package com.example.designsystem.parameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.model.CatalogModel
import com.example.model.CategoryModel
import com.example.model.ProductModel
import com.example.model.TagModel

class CatalogPreviewParameterProvider : PreviewParameterProvider<CatalogModel> {

    override val values: Sequence<CatalogModel> = sequenceOf(CatalogPreviewParameter.catalog)
}

object CatalogPreviewParameter{

    private val categories = listOf(
        CategoryModel(id = 676153, name = "Горячие блюда"),
        CategoryModel(id = 676154, name = "Суши"),
        CategoryModel(id = 676155, name = "Соусы"),
        CategoryModel(id = 676156, name = "Детское меню"),
        CategoryModel(id = 676157, name = "Подарочные сертификаты"),
        CategoryModel(id = 676159, name = "Напитки"),
        CategoryModel(id = 676160, name = "Горячие закуски"),
        CategoryModel(id = 676161, name = "Готовим дома"),
        CategoryModel(id = 676162, name = "Средства индивидуальной защиты"),
        CategoryModel(id = 676163, name = "Салаты"),
        CategoryModel(id = 676164, name = "Супы"),
        CategoryModel(id = 676165, name = "Десерты"),
        CategoryModel(id = 676166, name = "Вок"),
        CategoryModel(id = 676167, name = "Бургеры"),
        CategoryModel(id = 676168, name = "Роллы"),
        CategoryModel(id = 676169, name = "Наборы"),
        CategoryModel(id = 676170, name = "Сашими"),
        CategoryModel(id = 676171, name = "Половинки роллов"),
        CategoryModel(id = 676172, name = "Сувениры"),
        CategoryModel(id = 676173, name = "Бизнес ланчи"),
        CategoryModel(id = 1512275, name = "Фестиваль гёдза"),
        CategoryModel(id = 1667058, name = "Мероприятия")
    )
    private val tags = listOf(
        TagModel(id = 1, name = "Новинка"),
        TagModel(id = 2, name = "Вегетарианское блюдо"),
        TagModel(id = 3, name = "Хит!"),
        TagModel(id = 4, name = "Острое"),
        TagModel(id = 5, name = "Экспресс-меню")
    )
    private val products = listOf(
        ProductModel(
            id = 1,
            name = "Авокадо Кранч Маки 8шт",
            description = "Ролл с нежным мясом камчатского краба, копченой курицей и авокадо. Украшается соусом \"Унаги\" и легким майонезом. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 47000,
            priceOld = null,
            measure = 250,
            measureUnit = "г",
            energyPer100Grams = 307.8,
            proteinsPer100Grams = 6.1,
            fatsPer100Grams = 11.4,
            carbohydratesPer100Grams = 45.1,
            tags = listOf(),
            categoryId = 676168,
            countInCart = 3
        ),
        ProductModel(
            id = 8,
            name = "Сезам Ролл 8шт",
            description = "Урамаки ролл, украшенный кунжутом, с начинкой из тунца, обжаренного в соусе BBQ, огурца, сливочного сыра и зеленого лука. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 39000,
            priceOld = null,
            measure = 240,
            measureUnit = "г",
            energyPer100Grams = 277.2,
            proteinsPer100Grams = 9.5,
            fatsPer100Grams = 5.9,
            carbohydratesPer100Grams = 46.6,
            tags = listOf(),
            categoryId = 676168,
            countInCart = 0
        ),
        ProductModel(
            id = 9,
            name = "Митаки 8шт",
            description = "Традиционный ролл с огурцом и нежным сливочным сыром. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 23000,
            priceOld = 43511,
            measure = 150,
            measureUnit = "г",
            energyPer100Grams = 302.0,
            proteinsPer100Grams = 6.1,
            fatsPer100Grams = 3.7,
            carbohydratesPer100Grams = 61.1,
            tags = listOf(),
            categoryId = 676168,
            countInCart = 7
        ),
        ProductModel(
            id = 11,
            name = "Такеши Китано 8шт",
            description = "Острый ролл с муссом из лосося, омлетом, огурцом, сливочным сыром и икрой летучей рыбы с добавлением соуса спайси и табаско. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 58000,
            priceOld = 70133,
            measure = 275,
            measureUnit = "г",
            energyPer100Grams = 215.2,
            proteinsPer100Grams = 10.0,
            fatsPer100Grams = 3.9,
            carbohydratesPer100Grams = 34.9,
            tags = listOf(tags[3]),
            categoryId = 676168,
            countInCart = 2
        ),
        ProductModel(
            id = 12,
            name = "Филадельфия Кунжут 8шт",
            description = "Урамаки ролл в семенах кунжута с нежным лососем, сливочным сыром, огурцом и авокадо. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 46000,
            priceOld = null,
            measure = 225,
            measureUnit = "г",
            energyPer100Grams = 297.7,
            proteinsPer100Grams = 8.6,
            fatsPer100Grams = 8.4,
            carbohydratesPer100Grams = 46.9,
            tags = listOf(tags[2]),
            categoryId = 676168,
            countInCart = 0
        ),
        ProductModel(
            id = 13,
            name = "Йоко Оно 8шт",
            description = "Ролл с тунцом, огурцом и сливочным сыром, посыпанный стружкой тунца. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 33000,
            priceOld = 47845,
            measure = 200,
            measureUnit = "г",
            energyPer100Grams = 283.0,
            proteinsPer100Grams = 10.6,
            fatsPer100Grams = 3.0,
            carbohydratesPer100Grams = 53.3,
            tags = listOf(),
            categoryId = 676168,
            countInCart = 6
        ),
        ProductModel(
            id = 14,
            name = "Радуга 8шт",
            description = "Классический урамаки ролл из нежного лосося, тунца, сливочного сыра и огурца. Комплектуется бесплатным набором для роллов (Соевый соус Лайт 35г., васаби 6г., имбирь 15г.). +1 набор за каждые 600 рублей в заказе",
            image = "1.jpg",
            priceCurrent = 51000,
            priceOld = null,
            measure = 250,
            measureUnit = "г",
            energyPer100Grams = 241.5,
            proteinsPer100Grams = 10.1,
            fatsPer100Grams = 3.4,
            carbohydratesPer100Grams = 42.6,
            tags = listOf(tags[2]),
            categoryId = 676168,
            countInCart = 8
        )
    )

    val catalog = CatalogModel(
        categories = categories,
        products = CatalogPreviewParameter.products
    )
}

