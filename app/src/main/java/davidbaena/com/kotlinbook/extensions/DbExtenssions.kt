package davidbaena.com.kotlinbook.extensions

import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.SelectQueryBuilder

fun <T : Any> SelectQueryBuilder.parseList(parser: (Map<String, Any?>) -> T): List<T> =
        parseList(object : org.jetbrains.anko.db.MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun <T : Any> SelectQueryBuilder.parseOpt(parser: (Map<String, Any?>) -> T): T? =
        parseOpt(object : org.jetbrains.anko.db.MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
        })

fun SQLiteDatabase.clear(tableName: String) {
    execSQL("delete from $tableName")
}


fun <K, V : Any> MutableMap<K, V?>.toVarargArray():
        Array<out Pair<K, V>> = map({ Pair(it.key, it.value!!) }).toTypedArray()

fun SelectQueryBuilder.byId(id: Long)
        = whereSimple("_id = ?", id.toString())