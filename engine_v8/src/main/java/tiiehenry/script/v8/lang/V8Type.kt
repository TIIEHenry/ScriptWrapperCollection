package tiiehenry.script.v8.lang

import com.eclipsesource.v8.V8Value

enum class V8Type(val code: Int) {
    Null(V8Value.NULL),
    Integer(V8Value.INTEGER),
    Double(V8Value.DOUBLE),
    Boolean(V8Value.BOOLEAN),
    String(V8Value.STRING),
    V8Array(V8Value.V8_ARRAY),
    V8Object(V8Value.V8_OBJECT),

    V8Function(V8Value.V8_FUNCTION),
    V8TypedArray(V8Value.V8_TYPED_ARRAY),
    Byte(V8Value.BYTE),
    V8ArrayBuffer(V8Value.V8_ARRAY_BUFFER),
    UInt8Array(V8Value.UNSIGNED_INT_8_ARRAY),

    UInt8ClampedArray(V8Value.UNSIGNED_INT_8_CLAMPED_ARRAY),
    Int16Array(V8Value.INT_16_ARRAY),
    UInt16Array(V8Value.UNSIGNED_INT_16_ARRAY),
    UInt32Array(V8Value.UNSIGNED_INT_32_ARRAY),

    Float32Array(V8Value.FLOAT_32_ARRAY),
    Undefined(V8Value.UNDEFINED);

    companion object {
        fun valueOf(code: Int): V8Type? {
            for (value in values()) {
                if (value.code == code)
                    return value
            }
            return null
        }
    }
}