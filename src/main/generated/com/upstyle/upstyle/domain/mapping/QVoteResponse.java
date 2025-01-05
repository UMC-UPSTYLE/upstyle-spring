package com.upstyle.upstyle.domain.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QVoteResponse is a Querydsl query type for VoteResponse
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVoteResponse extends EntityPathBase<VoteResponse> {

    private static final long serialVersionUID = 1003141272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QVoteResponse voteResponse = new QVoteResponse("voteResponse");

    public final com.upstyle.upstyle.domain.common.QBaseEntity _super = new com.upstyle.upstyle.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.upstyle.upstyle.domain.QVoteOption option;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.upstyle.upstyle.domain.QUser user;

    public QVoteResponse(String variable) {
        this(VoteResponse.class, forVariable(variable), INITS);
    }

    public QVoteResponse(Path<? extends VoteResponse> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QVoteResponse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QVoteResponse(PathMetadata metadata, PathInits inits) {
        this(VoteResponse.class, metadata, inits);
    }

    public QVoteResponse(Class<? extends VoteResponse> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.option = inits.isInitialized("option") ? new com.upstyle.upstyle.domain.QVoteOption(forProperty("option"), inits.get("option")) : null;
        this.user = inits.isInitialized("user") ? new com.upstyle.upstyle.domain.QUser(forProperty("user")) : null;
    }

}

