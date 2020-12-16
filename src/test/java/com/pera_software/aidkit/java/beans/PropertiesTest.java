// Copyright 2018 Peter Most, PERA Software Solutions GmbH
//
// This file is part of the JavaAidKit library.
//
// JavaAidKit is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// JavaAidKit is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with JavaAidKit. If not, see <http://www.gnu.org/licenses/>.

package com.pera_software.aidkit.java.beans;

import static com.pera_software.aidkit.java.beans.Properties.getNonNullOrDefault;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author P. Most
 *
 */
public class PropertiesTest {

    private static class Order {
        Customer getCustomer() {
            return null;
        }
    }

    private static class Customer {
        Address getAddress() {
            return null;
        }
    }

    private static class Address {
        String getStreet() {
            return null;
        }
    }

    private static final Customer CUSTOMER_RESULT = new Customer();
    private static final Address  ADDRESS_RESULT = new Address();
    private static final String   STREET_RESULT = "";

    private static final Customer DEFAULT_CUSTOMER = new Customer();
    private static final Address  DEFAULT_ADDRESS = new Address();
    private static final String   DEFAULT_STREET = "";

    private AutoCloseable _mocks;
    @Mock
    private Order orderMock;

    @Mock
    private Customer customerMock;

    @Mock
    private Address addressMock;

    @BeforeEach
    public void openMocks() {
        _mocks = MockitoAnnotations.openMocks(this);
    }
    
    @AfterEach
    public void closeMocks() throws Exception {
    	_mocks.close();
    }

    @Test
    public void testGetNonNullOrDefault_OneGetter_WithNullInstance() {
        Customer customer = getNonNullOrDefault(null, Order::getCustomer, DEFAULT_CUSTOMER);
        assertSame(DEFAULT_CUSTOMER, customer);
    }

    @Test
    public void testGetNonNullOrDefault_OneGetter_WithNullResult() {
        Customer customer = getNonNullOrDefault(orderMock, Order::getCustomer, DEFAULT_CUSTOMER);
        assertSame(DEFAULT_CUSTOMER, customer);
    }

    @Test
    public void testGetNonNullOrDefault_OneGetter_WithNonNullResult() {
        when(orderMock.getCustomer()).thenReturn(CUSTOMER_RESULT);

        Customer customer = getNonNullOrDefault(orderMock, Order::getCustomer, DEFAULT_CUSTOMER);
        assertSame(CUSTOMER_RESULT, customer);
    }



    @Test
    public void testGetNonNullOrDefault_TwoGetter_WithNullInstance() {
        Address address = getNonNullOrDefault(null, Order::getCustomer, Customer::getAddress, DEFAULT_ADDRESS);
        assertSame(DEFAULT_ADDRESS, address);
    }

    @Test
    public void testGetNonNullOrDefault_TwoGetter_WithNullResult() {
        Address address = getNonNullOrDefault(null, Order::getCustomer, Customer::getAddress, DEFAULT_ADDRESS);
        assertSame(DEFAULT_ADDRESS, address);
    }

    @Test
    public void testGetNonNullOrDefault_TwoGetter_WithNonNullResult() {
        when(customerMock.getAddress()).thenReturn(ADDRESS_RESULT);
        when(orderMock.getCustomer()).thenReturn(customerMock);

        Address address = getNonNullOrDefault(orderMock, Order::getCustomer, Customer::getAddress, DEFAULT_ADDRESS);
        assertSame(ADDRESS_RESULT, address);
    }



    @Test
    public void testGetNonNullOrDefault_ThreeGetter_WithNullInstance() {
        String street = getNonNullOrDefault(null, Order::getCustomer, Customer::getAddress, Address::getStreet, DEFAULT_STREET);
        assertSame(DEFAULT_STREET, street);
    }

    @Test
    public void testGetNonNullOrDefault_ThreeGetter_WithNullResult() {
        String street = getNonNullOrDefault(null, Order::getCustomer, Customer::getAddress, Address::getStreet, DEFAULT_STREET);
        assertSame(DEFAULT_STREET, street);
    }

    @Test
    public void testGetNonNullOrDefault_ThreeGetter_WithNonNullResult() {
        when(addressMock.getStreet()).thenReturn(STREET_RESULT);
        when(customerMock.getAddress()).thenReturn(ADDRESS_RESULT);
        when(orderMock.getCustomer()).thenReturn(customerMock);

        String street = getNonNullOrDefault(orderMock, Order::getCustomer, Customer::getAddress, Address::getStreet, DEFAULT_STREET);
        assertSame(STREET_RESULT, street);
    }
}
