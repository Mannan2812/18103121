package Test.Project;
import java.net.URL;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class App { 
	static XSSFWorkbook workbook;
	static XSSFSheet sheet,facultySheet,downloadsSheet;
	static HashSet<String> urls = new HashSet<String>();
	static HashSet<String> downloadable_urls = new HashSet<String>();
	static String Baseurl;
	static String HOME_URL;
	static int MAX_DEPTH = 8;
	static int row_num = 0,facultRowNum = 0;
	public static boolean checkURL(String url) {
		if(url.contains("#"))
			return false;
		
		if(url.contains(HOME_URL))
			return true;
		
		return false;
	}
	
	public static void downloadFiles() {
		int pdfName = 1, docxName = 1;
		
		try {
			int row_num_download = 0;
			Row row = downloadsSheet.createRow(row_num_download);
			Cell cell = row.createCell(0);
			cell.setCellValue("Link");
			cell = row.createCell(1);
			cell.setCellValue("Type");
			File file = new File("./pdf");
			file.mkdir();
			file = new File("./docs");
			file.mkdir();
			for(String url: downloadable_urls) {
				++row_num_download;
				row = downloadsSheet.createRow(row_num_download);
				cell = row.createCell(0);
				cell.setCellValue(url);
				cell = row.createCell(1);
				URL u = new URL(url);
				InputStream in = u.openStream();
    			ReadableByteChannel rbc = Channels.newChannel(in);
    			FileOutputStream fos;
	    		if(url.contains(".pdf")) {
	    			cell.setCellValue("PDF");
	    			fos = new FileOutputStream("./pdf/PDF"+Integer.toString(pdfName)+".pdf");
	    			++pdfName;
	    			fos.getChannel().transferFrom(rbc, 0,Long.MAX_VALUE);
	    		}
	    		else if(url.contains(".docx")) {
	    			cell.setCellValue("DOCX");
	    			 fos = new FileOutputStream("./docs/DOCX"+Integer.toString(docxName)+".docx");
	    			++docxName;
	    			fos.getChannel().transferFrom(rbc, 0,Long.MAX_VALUE);
	    		}
	    		else if(url.contains("youtube")) {
	    			cell.setCellValue("Video");
	    		}
	    	}
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public static void fetchURLs(String url,int depth) { 
		try {
			
			if(!checkURL(url))
				return;
			
			if(urls.contains(url)) {
				return;
			}
			if(url.contains(".pdf")||url.contains(".docx")) {
				downloadable_urls.add(url);
				return;
			}
			urls.add(url);
			if(depth>=MAX_DEPTH)
				return;
			Document doc = Jsoup.connect(url).get();
			if(url.contains("faculty")) {
				++facultRowNum;
				Row row = facultySheet.createRow(facultRowNum);
				Cell cell = row.createCell(0);
				cell.setCellValue(url);
			}
			else if(doc.toString().contains("Research Interests")) {
				++facultRowNum;
				Row row = facultySheet.createRow(facultRowNum);
				Cell cell = row.createCell(0);
				cell.setCellValue(url);
			}
			Elements urlList = doc.select("a[href]");
	    	Elements files = doc.select("iframe");
	    	for(Element f:files)
	    		downloadable_urls.add(f.absUrl("src"));
	    	Elements data = doc.select("p");
	    	for(Element p:data) {
	    		if(p.hasText()) {
		    		++row_num;
		    		Row row = sheet.createRow(row_num);
		    		Cell cell = row.createCell(0);
		    		cell.setCellValue(url);
		    		cell = row.createCell(1);
		    		cell.setCellValue(p.text());
	    		}
	    	}
	    	for(Element u:urlList) {
	    		String absURL = u.absUrl("href");
	    		fetchURLs(absURL,depth+1);
	    	}
			
		} catch(IOException e) {
			System.out.println(e);
		}
	}
    public static void main(String args[]) { 
    	Scanner in = new Scanner(System.in);
    	try {
	    	workbook = new XSSFWorkbook();
	    	sheet = workbook.createSheet("<p> tags and Urls");
	    	facultySheet = workbook.createSheet("Faculty URLs");
	    	downloadsSheet = workbook.createSheet("Download Links");
	    	XSSFRow row;
	    	row = sheet.createRow(row_num);
	    	Cell cell = row.createCell(0);
	    	cell.setCellValue("URL");
	    	cell = row.createCell(1);
	    	cell.setCellValue("TEXT IN <p> TAG");
	    	row = facultySheet.createRow(facultRowNum);
	    	cell = row.createCell(0);
	    	cell.setCellValue("URL of faculty specific pages");
	    	System.out.println("=====================");
	    	System.out.println("PS: I have handled only docx and pdf extensions so you might see an exception for other mime types. Please specify depth judiciously as this crawler will download files which is proportional to the depth specified.\n"
	    			+ "In the directory in which the program runs, two directories namely pdf and docs will be created and one file named abc.xlsx will be created. In xlsx file there are 3 separate sheets. Kindly check them all.");
	    	System.out.println("=====================");
	    	
	    	System.out.println("Welcome to WEB Crawler");
	    	System.out.println("Enter the HOME url of page you want to crawl for:");
	    	Baseurl= in.next();
	    	System.out.println("Enter the depth of crawler:");
	    	MAX_DEPTH = in.nextInt();
	    	System.out.println("Please enter non-zero number to download the files (Enabling this will take more time) otherwise enter zero");
	    	int downloadFiles = in.nextInt();
	    	try {
	    	HOME_URL = new URL(new URL(Baseurl),"/").toString();
	    	} catch(Exception e) {
	    		System.out.println("Invalid URL");
	    		return;
	    	}
	    	System.out.println();
	    	fetchURLs(Baseurl,0);
	    	if(downloadFiles!=0)
	    	downloadFiles();
	    	FileOutputStream out = new FileOutputStream(
	    			new File("./abc.xlsx"));
	    	workbook.write(out);
	    	out.close();
	    	workbook.close();
	    	System.out.println("Crawler finished its work");
    	} catch (Exception e) {
			System.out.println(e);
		}
    	

    }
}