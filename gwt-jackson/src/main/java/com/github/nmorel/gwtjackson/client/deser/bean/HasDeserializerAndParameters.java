/*
 * Copyright 2014 Nicolas Morel
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

package com.github.nmorel.gwtjackson.client.deser.bean;

import com.github.nmorel.gwtjackson.client.JsonDeserializationContext;
import com.github.nmorel.gwtjackson.client.JsonDeserializer;
import com.github.nmorel.gwtjackson.client.JsonDeserializerParameters;
import com.github.nmorel.gwtjackson.client.stream.JsonReader;

/**
 * Lazy initialize a {@link JsonDeserializer}
 *
 * @author Nicolas Morel
 * @version $Id: $
 */
public abstract class HasDeserializerAndParameters<V, S extends JsonDeserializer<V>> extends HasDeserializer<V, S> {

    private JsonDeserializerParameters parameters;

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return a {@link com.github.nmorel.gwtjackson.client.JsonDeserializerParameters} object.
     */
    protected JsonDeserializerParameters getParameters() {
        if ( null == parameters ) {
            parameters = newParameters();
        }
        return parameters;
    }

    /**
     * <p>newParameters</p>
     *
     * @return a {@link com.github.nmorel.gwtjackson.client.JsonDeserializerParameters} object.
     */
    protected JsonDeserializerParameters newParameters() {
        return JsonDeserializerParameters.DEFAULT;
    }

    /**
     * Deserializes the property defined for this instance.
     *
     * @param reader reader
     * @param ctx context of the deserialization process
     * @return a V object.
     */
    public V deserialize( JsonReader reader, JsonDeserializationContext ctx ) {
        return getDeserializer().deserialize( reader, ctx, getParameters() );
    }
}
