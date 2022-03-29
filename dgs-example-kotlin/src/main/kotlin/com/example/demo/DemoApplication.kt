/*
 * Copyright 2021 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo

import com.example.demo.generated.types.Image
import com.example.demo.generated.types.Review
import com.example.demo.generated.types.Show
import com.example.demo.generated.types.SubmittedReview
import com.netflix.graphql.dgs.internal.DgsSchemaProvider
import graphql.schema.GraphQLSchema
import graphql.schema.visibility.GraphqlFieldVisibility
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.NativeDetector
import org.springframework.core.io.ClassPathResource
import org.springframework.nativex.hint.TypeAccess
import org.springframework.nativex.hint.TypeHint


@TypeHint(
    types = [Show::class, Review::class, SubmittedReview::class, Image::class],
    access = [TypeAccess.DECLARED_METHODS, TypeAccess.DECLARED_CONSTRUCTORS]
)

@SpringBootApplication
class DemoApplication {
    @Bean
    open fun schema(
        dgsSchemaProvider: DgsSchemaProvider,
        fieldVisibility: GraphqlFieldVisibility
    ): GraphQLSchema {
        if (NativeDetector.inNativeImage()) {
            // this part isn't great, but:
            // right now the DgsSchemaProvider used to 'find' the schema files on the classpath fails because
            // in the graalvm native image world there is NO classpath to speak of. So this code manually
            // adds a Resource, knowing that the default resolution logic fail. But, it only does so in
            // a GraalVM native image context
            return dgsSchemaProvider.schema(String(ClassPathResource("schema/schema.graphqls").inputStream.readAllBytes()))
        }

        return dgsSchemaProvider.schema(null, fieldVisibility)
    }

}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
