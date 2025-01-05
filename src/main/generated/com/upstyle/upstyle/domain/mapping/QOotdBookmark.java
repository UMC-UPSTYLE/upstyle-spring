package com.upstyle.upstyle.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotdBookmark is a Querydsl query type for OotdBookmark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotdBookmark extends EntityPathBase<OotdBookmark> {

    private static final long serialVersionUID = -42248973L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotdBookmark ootdBookmark = new QOotdBookmark("ootdBookmark");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.upstyle.upstyle.domain.QOotd ootd;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.upstyle.upstyle.domain.QUser user;

    public QOotdBookmark(String variable) {
        this(OotdBookmark.class, forVariable(variable), INITS);
    }

    public QOotdBookmark(Path<? extends OotdBookmark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotdBookmark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotdBookmark(PathMetadata metadata, PathInits inits) {
        this(OotdBookmark.class, metadata, inits);
    }

    public QOotdBookmark(Class<? extends OotdBookmark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ootd = inits.isInitialized("ootd") ? new com.upstyle.upstyle.domain.QOotd(forProperty("ootd"), inits.get("ootd")) : null;
        this.user = inits.isInitialized("user") ? new com.upstyle.upstyle.domain.QUser(forProperty("user")) : null;
    }

}

