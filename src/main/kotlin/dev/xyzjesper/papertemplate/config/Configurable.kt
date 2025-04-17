package dev.xyzjesper.papertemplate.config

interface Configurable {
    fun save()
    fun load() {}
    fun reset() {}
}