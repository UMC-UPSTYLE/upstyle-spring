package com.upstyle.upstyle.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotdResponseCloth is a Querydsl query type for OotdResponseCloth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotdResponseCloth extends EntityPathBase<OotdResponseCloth> {

    private static final long serialVersionUID = 2129197244L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotdResponseCloth ootdResponseCloth = new QOotdResponseCloth("ootdResponseCloth");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    public final com.upstyle.upstyle.domain.QCloth cloth;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.upstyle.upstyle.domain.QOotdResponse response;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOotdResponseCloth(String variable) {
        this(OotdResponseCloth.class, forVariable(variable), INITS);
    }

    public QOotdResponseCloth(Path<? extends OotdResponseCloth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotdResponseCloth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotdResponseCloth(PathMetadata metadata, PathInits inits) {
        this(OotdResponseCloth.class, metadata, inits);
    }

    public QOotdResponseCloth(Class<? extends OotdResponseCloth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cloth = inits.isInitialized("cloth") ? new com.upstyle.upstyle.domain.QCloth(forProperty("cloth"), inits.get("cloth")) : null;
        this.response = inits.isInitialized("response") ? new com.upstyle.upstyle.domain.QOotdResponse(forProperty("response"), inits.get("response")) : null;
    }

}

