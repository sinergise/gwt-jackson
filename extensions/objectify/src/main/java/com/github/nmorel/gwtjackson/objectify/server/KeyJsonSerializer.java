/*
 * Copyright 2017 Nicolas Morel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.nmorel.gwtjackson.objectify.server;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.googlecode.objectify.Key;

public class KeyJsonSerializer extends JsonSerializer<Key> {

    @Override
    public void serialize( Key value, JsonGenerator jgen, SerializerProvider provider ) throws IOException {
        if ( value != null || Include.NON_NULL != provider.getConfig().getDefaultPropertyInclusion().getValueInclusion() ) {
            StringWriter writer = new StringWriter();
            JsonGenerator jsonGenerator = jgen.getCodec().getFactory().createGenerator( writer );
            jgen.getCodec().writeValue( jsonGenerator, value );
            jsonGenerator.close();
            jgen.writeFieldName( writer.toString() );
        }
    }
}
