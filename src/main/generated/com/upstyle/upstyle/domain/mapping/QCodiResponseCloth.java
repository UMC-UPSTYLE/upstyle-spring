package com.upstyle.upstyle.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCodiResponseCloth is a Querydsl query type for CodiResponseCloth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCodiResponseCloth extends EntityPathBase<CodiResponseCloth> {

    private static final long serialVersionUID = -2104435525L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCodiResponseCloth codiResponseCloth = new QCodiResponseCloth("codiResponseCloth");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final com.upstyle.upstyle.domain.QCloth cloth;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.upstyle.upstyle.domain.QCodiResponse response;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCodiResponseCloth(String variable) {
        this(CodiResponseCloth.class, forVariable(variable), INITS);
    }

    public QCodiResponseCloth(Path<? extends CodiResponseCloth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCodiResponseCloth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCodiResponseCloth(PathMetadata metadata, PathInits inits) {
        this(CodiResponseCloth.class, metadata, inits);
    }

    public QCodiResponseCloth(Class<? extends CodiResponseCloth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cloth = inits.isInitialized("cloth") ? new com.upstyle.upstyle.domain.QCloth(forProperty("cloth"), inits.get("cloth")) : null;
        this.response = inits.isInitialized("response") ? new com.upstyle.upstyle.domain.QCodiResponse(forProperty("response"), inits.get("response")) : null;
    }

}

