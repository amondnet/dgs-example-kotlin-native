package org.springframework.experimental.graphql;

import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.TypeAccess;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.web.bind.annotation.Mapping;

@NativeHint(trigger = Mapping.class, types = {
    // TODO What about some way to say "all annotations in this package"
    @TypeHint(types = {
        DgsQuery.class,
        DgsMutation.class,
        DgsData.class,
    }, access = TypeAccess.PUBLIC_METHODS)
})
public class DgsAnnotationHints {
}
