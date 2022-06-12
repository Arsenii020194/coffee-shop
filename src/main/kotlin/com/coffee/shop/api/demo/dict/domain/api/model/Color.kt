package com.coffee.shop.api.demo.dict.domain.api.model

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.Id

@Entity
@Data
@NoArgsConstructor
class Color {
    @Id
    var code: String? = null
    var name: String? = null
}