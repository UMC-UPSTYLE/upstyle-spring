package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothColor is a Querydsl query type for ClothColor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothColor extends EntityPathBase<ClothColor> {

    private static final long serialVersionUID = -99251242L;

    public static final QClothColor clothColor = new QClothColor("clothColor");

    public final StringPath colorCode = createString("colorCode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QClothColor(String variable) {
        super(ClothColor.class, forVariable(variable));
    }

    public QClothColor(Path<? extends ClothColor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothColor(PathMetadata metadata) {
        super(ClothColor.class, metadata);
    }

}

