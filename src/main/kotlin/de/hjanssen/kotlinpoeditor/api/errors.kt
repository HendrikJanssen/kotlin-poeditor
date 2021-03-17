package de.hjanssen.kotlinpoeditor.api

class POEditorException(code: Int, message: String): RuntimeException("PoEditor error with code $code: $message")