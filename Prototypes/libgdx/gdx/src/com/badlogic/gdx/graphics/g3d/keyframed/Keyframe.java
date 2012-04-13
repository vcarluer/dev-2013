/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.graphics.g3d.keyframed;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

/** Container for the geometry of a single animation keyframe.
 * 
 * @author Dave Clayton <contact@redskyforge.com> */
public class Keyframe {
	public float[][] vertices = null;
	public short[][] indices = null;
	public boolean indicesSet = false;
	public boolean indicesSent = false;

	public Vector3[] taggedJointPos = null;
	public Quaternion[] taggedJoint = null;
}
