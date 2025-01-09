package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothCategory is a Querydsl query type for ClothCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothCategory extends EntityPathBase<ClothCategory> {

    private static final long serialVersionUID = -1176819797L;

    public static final QClothCategory clothCategory = new QClothCategory("clothCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QClothCategory(String variable) {
        super(ClothCategory.class, forVariable(variable));
    }

    public QClothCategory(Path<? extends ClothCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothCategory(PathMetadata metadata) {
        super(ClothCategory.class, metadata);
    }

}

