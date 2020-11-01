package com.programmersonly.mentorship.commons.events;

import io.vavr.control.Either;

public class EitherResult {

    public static <L, R>Either<L, R> announceFailure(L left){
        return Either.left(left);
    }

    public static <L, R>Either<L, R> announceSuccess(R right){
        return Either.right(right);
    }
}
