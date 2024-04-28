package com.sean.taipeizoo.common

fun String.toHttps(): String = this.replaceFirst("http://", "https://")

fun String.trimNewLineStart(): String = this.replace(Regex("\n\\s+"), "\n")