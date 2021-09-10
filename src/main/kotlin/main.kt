import java.io.File
import java.io.FileReader

fun main(args: Array<String>){
    val howMany = args[0].toInt()
    val r = Runtime.getRuntime()
    var progress = 1

    for (i in 1..howMany){
        val name = genHash(6)
        var p = r.exec("wget prnt.sc/$name >> $name")
        p.waitFor()
        val source = (FileReader(name).readLines().toString()).split("\"")
        File(name).delete()
        println("$progress. downloading ${source[83]}")
        p = r.exec("wget ${source[83]}")
        p.waitFor()
        progress++
    }
}

// todo: figure out compatible entry points, speed up the process
fun getCharacter(): Char{
    val whatFunction = (1..2).random()
    var whatToReturn = '\u0000'
    when (whatFunction) {
        1 -> whatToReturn = ('\u0031'..'\u0039').random()   // random number
        2 -> whatToReturn = ('\u0061'..'\u007A').random()   // random lowercase
    }
    return whatToReturn
}
fun genHash(size: Int): String{
    val carrier = CharArray(size)
    for (i in 0..size-1){
        carrier[i] = getCharacter()
    }
    return String(carrier)
}