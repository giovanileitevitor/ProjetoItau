package com.nm.infra.net.data

abstract class Mapper<CLASS_IN, CLASS_OUT> {
    abstract fun transform(item: CLASS_IN): CLASS_OUT
}
