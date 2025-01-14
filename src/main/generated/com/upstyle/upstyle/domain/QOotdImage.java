package com.upstyle.upstyle.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotdImage is a Querydsl query type for OotdImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotdImage extends EntityPathBase<OotdImage> {

    private static final long serialVersionUID = 1065474782L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotdImage ootdImage = new QOotdImage("ootdImage");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QOotd ootd;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QOotdImage(String variable) {
        this(OotdImage.class, forVariable(variable), INITS);
    }

    public QOotdImage(Path<? extends OotdImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotdImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotdImage(PathMetadata metadata, PathInits inits) {
        this(OotdImage.class, metadata, inits);
    }

    public QOotdImage(Class<? extends OotdImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ootd = inits.isInitialized("ootd") ? new QOotd(forProperty("ootd"), inits.get("ootd")) : null;
    }

}

