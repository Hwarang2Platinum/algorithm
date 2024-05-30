readLine()
let n=readLine()!.split{$0==" "}.sorted{$0+$1>$1+$0}.joined()
print(n.first!=="0" ?0:n)