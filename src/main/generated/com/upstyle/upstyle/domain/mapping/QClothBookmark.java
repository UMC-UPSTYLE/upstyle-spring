package com.upstyle.upstyle.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClothBookmark is a Querydsl query type for ClothBookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QClothBookmark extends EntityPathBase<ClothBookmark> {

    private static final long serialVersionUID = -1893359677L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClothBookmark clothBookmark = new QClothBookmark("clothBookmark");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final com.upstyle.upstyle.domain.QCloth cloth;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.upstyle.upstyle.domain.QUser user;

    public QClothBookmark(String variable) {
        this(ClothBookmark.class, forVariable(variable), INITS);
    }

    public QClothBookmark(Path<? extends ClothBookmark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClothBookmark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClothBookmark(PathMetadata metadata, PathInits inits) {
        this(ClothBookmark.class, metadata, inits);
    }

    public QClothBookmark(Class<? extends ClothBookmark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cloth = inits.isInitialized("cloth") ? new com.upstyle.upstyle.domain.QCloth(forProperty("cloth"), inits.get("cloth")) : null;
        this.user = inits.isInitialized("user") ? new com.upstyle.upstyle.domain.QUser(forProperty("user")) : null;
    }

}

