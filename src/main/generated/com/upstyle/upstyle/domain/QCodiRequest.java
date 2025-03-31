package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodiRequest is a Querydsl query type for CodiRequest
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCodiRequest extends EntityPathBase<CodiRequest> {

    private static final long serialVersionUID = -29991279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodiRequest codiRequest = new QCodiRequest("codiRequest");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final StringPath body = createString("body");

    public final ListPath<CodiResponse, QCodiResponse> codiResponseList = this.<CodiResponse, QCodiResponse>createList("codiResponseList", CodiResponse.class, QCodiResponse.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Integer> responseCount = createNumber("responseCount", Integer.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final QUser user;

    public QCodiRequest(String variable) {
        this(CodiRequest.class, forVariable(variable), INITS);
    }

    public QCodiRequest(Path<? extends CodiRequest> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodiRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodiRequest(PathMetadata metadata, PathInits inits) {
        this(CodiRequest.class, metadata, inits);
    }

    public QCodiRequest(Class<? extends CodiRequest> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

