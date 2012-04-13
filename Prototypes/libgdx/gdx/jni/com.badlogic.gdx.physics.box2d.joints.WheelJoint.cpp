#include <com.badlogic.gdx.physics.box2d.joints.WheelJoint.h>

//@line:11

#include <Box2D/Box2D.h> 
	 JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetJointTranslation(JNIEnv* env, jobject object, jlong addr) {


//@line:24

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetJointTranslation();
	

}

JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetJointSpeed(JNIEnv* env, jobject object, jlong addr) {


//@line:34

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetJointSpeed();
	

}

JNIEXPORT jboolean JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniIsMotorEnabled(JNIEnv* env, jobject object, jlong addr) {


//@line:44

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->IsMotorEnabled();
	

}

JNIEXPORT void JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniEnableMotor(JNIEnv* env, jobject object, jlong addr, jboolean flag) {


//@line:54

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		joint->EnableMotor(flag);
	

}

JNIEXPORT void JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniSetMotorSpeed(JNIEnv* env, jobject object, jlong addr, jfloat speed) {


//@line:64

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		joint->SetMotorSpeed(speed);
	

}

JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetMotorSpeed(JNIEnv* env, jobject object, jlong addr) {


//@line:74

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetMotorSpeed();
	

}

JNIEXPORT void JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniSetMaxMotorTorque(JNIEnv* env, jobject object, jlong addr, jfloat torque) {


//@line:84

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		joint->SetMaxMotorTorque(torque);
	

}

JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetMaxMotorTorque(JNIEnv* env, jobject object, jlong addr) {


//@line:93

		b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetMaxMotorTorque();
	

}

JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetMotorTorque(JNIEnv* env, jobject object, jlong addr, jfloat invDt) {


//@line:103

	  	b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetMotorTorque(invDt);
	

}

JNIEXPORT void JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniSetSpringFrequencyHz(JNIEnv* env, jobject object, jlong addr, jfloat hz) {


//@line:113

		b2WheelJoint* joint = (b2WheelJoint*)addr;
		joint->SetSpringFrequencyHz(hz);
	

}

JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetSpringFrequencyHz(JNIEnv* env, jobject object, jlong addr) {


//@line:122

		b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetSpringFrequencyHz();
	

}

JNIEXPORT void JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniSetSpringDampingRatio(JNIEnv* env, jobject object, jlong addr, jfloat ratio) {


//@line:132

		b2WheelJoint* joint = (b2WheelJoint*)addr;
		joint->SetSpringDampingRatio(ratio);
	

}

JNIEXPORT jfloat JNICALL Java_com_badlogic_gdx_physics_box2d_joints_WheelJoint_jniGetSpringDampingRatio(JNIEnv* env, jobject object, jlong addr) {


//@line:141

		b2WheelJoint* joint = (b2WheelJoint*)addr;
		return joint->GetSpringDampingRatio();
	

}

