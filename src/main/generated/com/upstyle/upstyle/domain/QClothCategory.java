package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClothCategory is a Querydsl query type for ClothCategory
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothCategory extends EntityPathBase<ClothCategory> {

    private static final long serialVersionUID = -1176819797L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClothCategory clothCategory = new QClothCategory("clothCategory");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QClothKind kind;

    public final StringPath name = createString("name");

    public QClothCategory(String variable) {
        this(ClothCategory.class, forVariable(variable), INITS);
    }

    public QClothCategory(Path<? extends ClothCategory> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClothCategory(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClothCategory(PathMetadata metadata, PathInits inits) {
        this(ClothCategory.class, metadata, inits);
    }

    public QClothCategory(Class<? extends ClothCategory> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.kind = inits.isInitialized("kind") ? new QClothKind(forProperty("kind")) : null;
    }

}

