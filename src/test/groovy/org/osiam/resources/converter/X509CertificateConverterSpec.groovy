/**
 * The MIT License (MIT)
 *
 * Copyright (C) 2013-2016 tarent solutions GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.osiam.resources.converter

import org.osiam.resources.scim.MultiValuedAttribute
import org.osiam.resources.scim.X509Certificate
import org.osiam.storage.entities.X509CertificateEntity
import spock.lang.Specification

class X509CertificateConverterSpec extends Specification {

    X509CertificateEntity entity
    MultiValuedAttribute attribute
    X509CertificateConverter converter

    def setup() {
        def value = 'example'
        def display = 'mycert'

        entity = new X509CertificateEntity()
        entity.setValue(value)
        entity.setDisplay(display)

        attribute = new X509Certificate.Builder()
                .setValue(value)
                .setDisplay(display)
                .build()

        converter = new X509CertificateConverter()
    }

    def 'convert entity to SCIM X509Certificate'() {
        when:
        def attribute = converter.toScim(entity)

        then:
        attribute.equals(this.attribute)
    }

    def 'convert SCIM X509Certificate to entity'() {
        when:
        def entity = converter.fromScim(this.attribute)

        then:
        entity == this.entity
    }
}
