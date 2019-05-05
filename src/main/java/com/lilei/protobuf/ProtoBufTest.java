package com.lilei.protobuf;

public class ProtoBufTest {
	public static void  main(String[] args) throws Exception{
		DataInfo.Student student = DataInfo.Student.newBuilder().
				setName("lilei").setAge(23).setAddress("zhuhai").build();

		byte[] student2ByteArray = student.toByteArray();

		DataInfo.Student  student1 = DataInfo.Student.parseFrom(student2ByteArray);
		System.out.println(student2ByteArray);
		System.out.println(student1);
	}
}
