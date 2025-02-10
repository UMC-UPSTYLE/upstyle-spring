package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodiResponse is a Querydsl query type for CodiResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCodiResponse extends EntityPathBase<CodiResponse> {

    private static final long serialVersionUID = -876795777L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodiResponse codiResponse = new QCodiResponse("codiResponse");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final StringPath body = createString("body");

    public final ListPath<com.upstyle.upstyle.domain.mapping.CodiResponseCloth, com.upstyle.upstyle.domain.mapping.QCodiResponseCloth> codiResponseClothList = this.<com.upstyle.upstyle.domain.mapping.CodiResponseCloth, com.upstyle.upstyle.domain.mapping.QCodiResponseCloth>createList("codiResponseClothList", com.upstyle.upstyle.domain.mapping.CodiResponseCloth.class, com.upstyle.upstyle.domain.mapping.QCodiResponseCloth.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ImageUrl = createString("ImageUrl");

    public final QCodiRequest request;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QCodiResponse(String variable) {
        this(CodiResponse.class, forVariable(variable), INITS);
    }

    public QCodiResponse(Path<? extends CodiResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodiResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodiResponse(PathMetadata metadata, PathInits inits) {
        this(CodiResponse.class, metadata, inits);
    }

    public QCodiResponse(Class<? extends CodiResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.request = inits.isInitialized("request") ? new QCodiRequest(forProperty("request"), inits.get("request")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

