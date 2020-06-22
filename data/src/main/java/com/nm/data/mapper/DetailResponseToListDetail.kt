package com.nm.data.mapper

import com.nm.data.entity.Detail
import com.nm.data.entity.User
import com.nm.data.model.DetailSchema
import com.nm.data.model.UserSchema
import com.nm.data.model.response.DetailsResponse
import com.nm.infra.net.data.Mapper

class DetailResponseToListDetail(
    private val userMapper: Mapper<UserSchema, User>
) : Mapper<List<DetailSchema>, List<Detail>>() {

    override fun transform(item: List<DetailSchema>): List<Detail> {
        val details = mutableListOf<Detail>()

        item.forEach {
            details.add(
                Detail(
                    url = it.url,
                    id = it.id,
                    number = it.number,
                    state = it.state,
                    title = it.title,
                    user = userMapper.transform(it.user),
                    body = it.body
                )
            )
        }

        return details
    }
}
