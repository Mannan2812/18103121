/**
 * Please Run this as chrome snippet
 */

function checkParenthesis(str){
    var stack = []
    var top = -1
    for(var i = 0;i<str.length;++i){
        
        if(str[i]=='['||str[i] == '(' || str[i] == '{'){
            ++top
            stack[top] = str[i];
        }
        else if(str[i]==']'){
            if(top==-1 || stack[top]!='['){
            console.log("in 2")
                return false
            }
            --top
        }
        else if(str[i]=='}'){
            if(top==-1 || stack[top]!='{'){
                return false
            }
            --top

        }
        else if(str[i]==')'){
            if(top==-1 || stack[top]!='('){
                console.log("in 4")
                return false
            }
            --top

        }
        else return false
    }
    return true
}
var str = prompt("Enter you string")
console.log(checkParenthesis(str))