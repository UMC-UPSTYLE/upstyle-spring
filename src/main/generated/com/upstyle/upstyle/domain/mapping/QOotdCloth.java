package com.upstyle.upstyle.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOotdCloth is a Querydsl query type for OotdCloth
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOotdCloth extends EntityPathBase<OotdCloth> {

    private static final long serialVersionUID = 972394365L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOotdCloth ootdCloth = new QOotdCloth("ootdCloth");

    public final com.upstyle.upstyle.domain.QCloth cloth;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.upstyle.upstyle.domain.QOotd ootd;

    public QOotdCloth(String variable) {
        this(OotdCloth.class, forVariable(variable), INITS);
    }

    public QOotdCloth(Path<? extends OotdCloth> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOotdCloth(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOotdCloth(PathMetadata metadata, PathInits inits) {
        this(OotdCloth.class, metadata, inits);
    }

    public QOotdCloth(Class<? extends OotdCloth> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.cloth = inits.isInitialized("cloth") ? new com.upstyle.upstyle.domain.QCloth(forProperty("cloth"), inits.get("cloth")) : null;
        this.ootd = inits.isInitialized("ootd") ? new com.upstyle.upstyle.domain.QOotd(forProperty("ootd"), inits.get("ootd")) : null;
    }

}

