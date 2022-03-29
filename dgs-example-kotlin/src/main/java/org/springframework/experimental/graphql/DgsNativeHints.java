package org.springframework.experimental.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.netflix.graphql.dgs.internal.DefaultDgsQueryExecutor;
import com.netflix.graphql.dgs.internal.DgsSchemaProvider;
import com.netflix.graphql.dgs.mvc.DgsRestController;
import com.netflix.graphql.dgs.mvc.DgsRestSchemaJsonController;
import com.netflix.graphql.dgs.mvc.ServletCookieValueResolver;
import com.netflix.graphql.dgs.webmvc.autoconfigure.DgsWebMvcAutoConfiguration;
import graphql.schema.visibility.GraphqlFieldVisibility;
import javax.servlet.Servlet;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.nativex.hint.NativeHint;
import org.springframework.nativex.hint.ResourceHint;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.nativex.type.NativeConfiguration;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import static org.springframework.nativex.hint.TypeAccess.DECLARED_CLASSES;
import static org.springframework.nativex.hint.TypeAccess.DECLARED_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.DECLARED_FIELDS;
import static org.springframework.nativex.hint.TypeAccess.DECLARED_METHODS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_CLASSES;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_CONSTRUCTORS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_FIELDS;
import static org.springframework.nativex.hint.TypeAccess.PUBLIC_METHODS;

/**
 * Provides Spring Native hints to support using Spring GraphQL in a Spring Native application.
 *
 * @author <a href="mailto:josh@joshlong.com">Josh Long</a>
 */
@TypeHint(
    typeNames = {
        "com.netflix.graphql.dgs.internal.DefaultDgsQueryExecutor$ReloadSchemaIndicator"
    },
    types = {
        com.netflix.graphql.dgs.internal.QueryValueCustomizer.class,
        com.netflix.graphql.dgs.internal.BaseDgsQueryExecutor.class,
        com.netflix.graphql.dgs.internal.DataFetcherInvoker.class,
        com.netflix.graphql.dgs.internal.DataFetcherResultProcessor.class,
        com.netflix.graphql.dgs.internal.DefaultDgsGraphQLContextBuilder.class,
        com.netflix.graphql.dgs.internal.DefaultInputObjectMapper.class,
        com.netflix.graphql.dgs.internal.DefaultRequestData.class,
        com.netflix.graphql.dgs.internal.DgsDataLoaderProvider.class,
        com.netflix.graphql.dgs.internal.DgsRequestData.class,
        com.netflix.graphql.dgs.internal.DgsSchemaProvider.class,
        com.netflix.graphql.dgs.internal.DgsWebMvcRequestData.class,
        com.netflix.graphql.dgs.internal.EntityFetcherRegistry.class,
        com.netflix.graphql.dgs.internal.FluxDataFetcherResultProcessor.class,
        com.netflix.graphql.dgs.internal.InputObjectMapper.class,
        com.netflix.graphql.dgs.internal.MonoDataFetcherResultProcessor.class,
        com.netflix.graphql.dgs.internal.QueryValueCustomizer.class,
        GraphqlFieldVisibility.class,
        graphql.execution.preparsed.PreparsedDocumentProvider.class,
        DgsRestController.class,
        DgsRestSchemaJsonController.class,
        ServletCookieValueResolver.class,
    }, //
    access = {//
        PUBLIC_CLASSES, PUBLIC_CONSTRUCTORS, PUBLIC_FIELDS, PUBLIC_METHODS,
        DECLARED_CLASSES, DECLARED_CONSTRUCTORS, DECLARED_FIELDS, DECLARED_METHODS
    } //
)

@NativeHint(trigger = DgsWebMvcAutoConfiguration.class,
    types = {
        @TypeHint(types = Servlet.class, access = {}),
        @TypeHint(types = ConfigurableWebApplicationContext.class, access = {}),
        @TypeHint(types = DgsRestController.class),
        @TypeHint(types = DgsRestSchemaJsonController.class),
        @TypeHint(types = ServletCookieValueResolver.class),
    })
@NativeHint(trigger = DgsAutoConfiguration.class,
    types = {
        @TypeHint(types = ObjectMapper.class, access = {}),
        @TypeHint(types = DefaultDgsQueryExecutor.class, access = {}),
        @TypeHint(types = DgsSchemaProvider.class),
        @TypeHint(types = DgsRestSchemaJsonController.class),
        @TypeHint(types = ServletCookieValueResolver.class),
    })
@ResourceHint(patterns = {"schema/*.graphqls"})
public class DgsNativeHints implements NativeConfiguration {
}