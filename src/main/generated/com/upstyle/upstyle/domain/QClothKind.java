package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QClothKind is a Querydsl query type for ClothKind
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothKind extends EntityPathBase<ClothKind> {

    private static final long serialVersionUID = 689767617L;

    public static final QClothKind clothKind = new QClothKind("clothKind");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QClothKind(String variable) {
        super(ClothKind.class, forVariable(variable));
    }

    public QClothKind(Path<? extends ClothKind> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClothKind(PathMetadata metadata) {
        super(ClothKind.class, metadata);
    }

}

