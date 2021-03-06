package com.coffee.shop.api.demo.image.impl

import com.coffee.shop.api.demo.image.api.ImageService
import org.springframework.core.io.DefaultResourceLoader
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ImageServiceImpl(
    private val resourceLoader: ResourceLoader = DefaultResourceLoader()
) : ImageService {

    override fun list(id: String): ByteArray {
        return resourceLoader
            .getResource("classpath:/db/changelog/images/list/$id.jpg")
            .inputStream
            .readBytes()
    }

    override fun card(id: String): List<ByteArray> {
        val resources =
            ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
                .getResources("classpath:/db/changelog/images/card/$id/*")
        return Arrays.stream(resources).map { it.inputStream.readBytes() }
            .collect(Collectors.toList())
    }

    override fun card(ids: Array<String>): Map<String, List<ByteArray>> {
        val result = HashMap<String, List<ByteArray>>()
        ids.forEach { result[it] = card(it) }
        return result
    }
}