package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothFit is a Querydsl query type for ClothFit
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothFit extends EntityPathBase<ClothFit> {

    private static final long serialVersionUID = 2100455748L;

    public static final QClothFit clothFit = new QClothFit("clothFit");

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath name = createString("name");

    public QClothFit(String variable) {
        super(ClothFit.class, forVariable(variable));
    }

    public QClothFit(Path<? extends ClothFit> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothFit(PathMetadata metadata) {
        super(ClothFit.class, metadata);
    }

}

