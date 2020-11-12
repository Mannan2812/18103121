function printChristmasTree(treeSize) {
    var spaceSize = treeSize;
    for (var i = 1; i <= treeSize; ++i) {

        var str = ""
        for (var k = 0; k < treeSize - i + 1; ++k)
            str += ' '
        for (var j = 1; j <= 2 * i - 1; ++j) {
            if(i==1)
            str += '*'
            else str += '0'
        }
        console.log(str);
    }
    var str = ""
    for (var k = 0; k < treeSize; ++k)
        str += ' '
    str += '0\n'
    str += str + str

    for (var k = 0; k < treeSize - 2; ++k)
        str += ' '
    str += "00000"
    console.log(str)

}

treeSize = prompt("Enter the size of tree ")

printChristmasTree(12)
