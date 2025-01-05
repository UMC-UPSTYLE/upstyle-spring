package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotdResponse is a Querydsl query type for OotdResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotdResponse extends EntityPathBase<OotdResponse> {

    private static final long serialVersionUID = -2024681506L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotdResponse ootdResponse = new QOotdResponse("ootdResponse");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final StringPath body = createString("body");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.upstyle.upstyle.domain.mapping.OotdResponseCloth, com.upstyle.upstyle.domain.mapping.QOotdResponseCloth> ootdResponseClothList = this.<com.upstyle.upstyle.domain.mapping.OotdResponseCloth, com.upstyle.upstyle.domain.mapping.QOotdResponseCloth>createList("ootdResponseClothList", com.upstyle.upstyle.domain.mapping.OotdResponseCloth.class, com.upstyle.upstyle.domain.mapping.QOotdResponseCloth.class, PathInits.DIRECT2);

    public final QOotdRequest request;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QOotdResponse(String variable) {
        this(OotdResponse.class, forVariable(variable), INITS);
    }

    public QOotdResponse(Path<? extends OotdResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotdResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotdResponse(PathMetadata metadata, PathInits inits) {
        this(OotdResponse.class, metadata, inits);
    }

    public QOotdResponse(Class<? extends OotdResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.request = inits.isInitialized("request") ? new QOotdRequest(forProperty("request"), inits.get("request")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

