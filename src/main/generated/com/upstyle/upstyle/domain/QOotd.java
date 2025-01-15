package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotd is a Querydsl query type for Ootd
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotd extends EntityPathBase<Ootd> {

    private static final long serialVersionUID = -323345283L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotd ootd = new QOotd("ootd");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.upstyle.upstyle.domain.mapping.OotdCloth, com.upstyle.upstyle.domain.mapping.QOotdCloth> ootdClothList = this.<com.upstyle.upstyle.domain.mapping.OotdCloth, com.upstyle.upstyle.domain.mapping.QOotdCloth>createList("ootdClothList", com.upstyle.upstyle.domain.mapping.OotdCloth.class, com.upstyle.upstyle.domain.mapping.QOotdCloth.class, PathInits.DIRECT2);

    public final ListPath<OotdImage, QOotdImage> ootdImageList = this.<OotdImage, QOotdImage>createList("ootdImageList", OotdImage.class, QOotdImage.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QOotd(String variable) {
        this(Ootd.class, forVariable(variable), INITS);
    }

    public QOotd(Path<? extends Ootd> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotd(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotd(PathMetadata metadata, PathInits inits) {
        this(Ootd.class, metadata, inits);
    }

    public QOotd(Class<? extends Ootd> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

