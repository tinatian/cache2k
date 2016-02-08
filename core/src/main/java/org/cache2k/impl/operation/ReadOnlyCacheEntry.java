package org.cache2k.impl.operation;

/*
 * #%L
 * cache2k core package
 * %%
 * Copyright (C) 2000 - 2016 headissue GmbH, Munich
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import org.cache2k.impl.ExceptionWrapper;

/**
 *
 *
 * @author Jens Wilke
 */
public class ReadOnlyCacheEntry<K, V> implements ResultEntry<K, V> {

  K key;
  V valueOrException;
  long lastModification;

  public ReadOnlyCacheEntry(final K _key, final V _valueOrException, final long _lastModification) {
    key = _key;
    lastModification = _lastModification;
    valueOrException = _valueOrException;
  }

  @Override
  public Throwable getException() {
    if (valueOrException instanceof ExceptionWrapper) {
      return ((ExceptionWrapper) valueOrException).getException();
    }
    return null;
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public long getLastModification() {
    return lastModification;
  }

  @Override
  public V getValue() {
    if (valueOrException instanceof ExceptionWrapper) {
      return null;
    }
    return valueOrException;
  }

  @Override
  public V getValueOrException() {
    return valueOrException;
  }

}
