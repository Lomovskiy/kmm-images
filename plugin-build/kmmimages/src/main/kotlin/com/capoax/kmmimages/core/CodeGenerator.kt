package com.capoax.kmmimages.core

import com.capoax.kmmimages.extensions.imageNameToConst

data class CodeGenerator(val packageName: String) {
    private val images = mutableListOf<String>()

    val result: String
        get() {
            return buildString {
                append("package $packageName\n")
                append("// Generated by CommonImages\n")
                append("data class Image(val name: String)\n")

                append("object Images {\n")
                val images = images.map { image ->
                    "\tval ${image.imageNameToConst()}: Image = Image(\"$image\")"
                }.joinToString { "\n" }
                append(images)
                append("}\n\n")
            }
        }

    fun addImage(name: String) {
        images.add(name)
    }
}
