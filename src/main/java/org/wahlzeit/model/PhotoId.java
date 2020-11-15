/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import java.util.Random;

/**
 * A photo id identifies a photo with a unique number.
 * The number has an equivalent string for web access.
 * This class also hands out the ids.
 */
public class PhotoId extends BaseId {

	/**
	 * 0 is never returned from nextValue; first value is 1
	 */
	protected static int currentId = 0;

	/**
	 *
	 */
	public static final PhotoId NULL_ID = new PhotoId(0);

	/**
	 *
	 */
	protected static PhotoId[] ids = new PhotoId[BUFFER_SIZE_INCREMENT];

	/**
	 * What a hack :-)
	 */
	public static final int ID_START = getFromString("x1abz") + 1 ;

	/**
	 *
	 */
	public static int getCurrentIdAsInt() {
		return currentId;
	}

	/**
	 *
	 */
	public static synchronized void setCurrentIdFromInt(int id) {
		currentId = id;
		ids = new PhotoId[currentId + BUFFER_SIZE_INCREMENT];
		ids[0] = NULL_ID;
	}

	/**
	 *
	 */
	public static synchronized int getNextIdAsInt() {
		currentId += 1;
		if (currentId >= ids.length) {
			PhotoId[] nids = new PhotoId[currentId + BUFFER_SIZE_INCREMENT];
			System.arraycopy(ids, 0, nids, 0, currentId);
			ids = nids;
		}
		return currentId;
	}

	/**
	 *
	 */
	public static PhotoId getIdFromInt(int id) {
		if ((id < 0) || (id > currentId)) {
			return NULL_ID;
		}

		// @FIXME http://en.wikipedia.org/wiki/Double-checked_locking
		PhotoId result = ids[id];
		if (result == null) {
			synchronized(ids) {
				result = ids[id];
				if (result == null) {
					result = new PhotoId(id);
					ids[id] = result;
				}
			}
		}

		return result;
	}

	/**
	 *
	 */
	public static PhotoId getIdFromString(String id) {
		return getIdFromInt(getFromString(id));
	}

	/**
	 *
	 */
	public static PhotoId getNextId() {
		return getIdFromInt(getNextIdAsInt());
	}

	/**
	 *
	 */
	public static PhotoId getRandomId() {
		int max = getCurrentIdAsInt() - 1;
		int id = randomNumber.nextInt();
		id = (id == Integer.MIN_VALUE) ? id ++ : id;
		id = (Math.abs(id) % max) + 1;
		return getIdFromInt(id);
	}

	/**
	 *
	 */
	protected PhotoId(int myValue) {
		value = myValue;
		stringValue = getFromInt(myValue);
	}

	/**
	 *
	 */
	public boolean isNullId() {
		return this == NULL_ID;
	}

}
