package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotdRequest is a Querydsl query type for OotdRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotdRequest extends EntityPathBase<OotdRequest> {

    private static final long serialVersionUID = 902811474L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotdRequest ootdRequest = new QOotdRequest("ootdRequest");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final StringPath body = createString("body");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<OotdResponse, QOotdResponse> ootdResponseList = this.<OotdResponse, QOotdResponse>createList("ootdResponseList", OotdResponse.class, QOotdResponse.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QOotdRequest(String variable) {
        this(OotdRequest.class, forVariable(variable), INITS);
    }

    public QOotdRequest(Path<? extends OotdRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotdRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotdRequest(PathMetadata metadata, PathInits inits) {
        this(OotdRequest.class, metadata, inits);
    }

    public QOotdRequest(Class<? extends OotdRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

