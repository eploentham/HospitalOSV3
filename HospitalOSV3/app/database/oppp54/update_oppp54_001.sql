-- create t_oppp54_notpass and insert data

CREATE TABLE t_oppp54_notpass (
    t_oppp54_notpass_id	varchar(255) NOT NULL,
    t_visit_id         	varchar(255) NULL,
    person_status      	varchar(1) NULL DEFAULT '0'::character varying,
    person_errcode     	varchar(255) NULL,
    service_status     	varchar(1) NULL DEFAULT '0'::character varying,
    service_errcode    	varchar(255) NULL,
    diag_status        	varchar(1) NULL DEFAULT '0'::character varying,
    diag_errcode       	varchar(255) NULL,
    drug_status        	varchar(1) NULL DEFAULT '0'::character varying,
    drug_errcode       	varchar(255) NULL,
    proced_status      	varchar(1) NULL DEFAULT '0'::character varying,
    proced_errcode     	varchar(255) NULL,
    epi_status         	varchar(1) NULL DEFAULT '0'::character varying,
    epi_errcode        	varchar(255) NULL,
    fp_status          	varchar(1) NULL DEFAULT '0'::character varying,
    fp_errcode         	varchar(255) NULL,
    anc_status         	varchar(1) NULL DEFAULT '0'::character varying,
    anc_errcode        	varchar(255) NULL,
    mch_status         	varchar(1) NULL DEFAULT '0'::character varying,
    mch_errcode        	varchar(255) NULL,
    pp_status          	varchar(1) NULL DEFAULT '0'::character varying,
    pp_errcode         	varchar(255) NULL,
    PRIMARY KEY(t_oppp54_notpass_id)
);

ALTER TABLE "t_oppp54_notpass" ADD CONSTRAINT "t_oppp54_notpass_ukey1" UNIQUE ("t_visit_id");

-- create b_oppp54_errcode
CREATE TABLE b_oppp54_errcode (
    code_id    	varchar(255) NOT NULL,
    description	varchar(255) NULL DEFAULT ''::character varying,
    remark     	varchar(255) NULL DEFAULT ''::character varying,
    hint       	varchar(255) NULL,
    PRIMARY KEY(code_id)
);
-- insert data b_oppp54_errcode
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9200', '�������ö������§�����źؤ���� (�����źؤ�ŵ�ͧ��ҹ��õ�Ǩ�ͺ)', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9201', '���������˹��º�ԡ������������ ʹ�. (PCUCODE)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9205', '�������������㹻է�����ҳ����˹�', '��Ǩ�ͺ�������ѹ�������ԡ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9207', '�������˭ԧ  (�����Ҩ�������ö������§������� PERSON ��)', '��������������˭ԧ ���� ��Ǩ�ͺ���������� PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9240', '���������������ҧ 9-60 ��', '�к����������ҧ 9-60 ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9241', '���ؤ���� (GA) ������������ҧ 4 - 45 �ѻ����', '�к����ؤ���� (GA) �����ҧ 4 - 45 �ѻ����', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN9299', '�����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1103', '������ PID �դ����ҧ (null)', '�������� PID �������繤����ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9200', '�������ö������§�������ԡ����', '��Ǩ�ͺ�����š������ԡ���������ö������§�������ԡ����������� (PCUCODE,PID,SEQ,DATE_SERV,CLINIC)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9230', '����������ä� ICD10 (WHO 2007) ���� ICD10 TM ����ᾷ��Ἱ��', '��Ǩ�ͺ�����ä������ͧ���  ICD10 (WHO 2007) ���� ICD10 TM ����ᾷ��Ἱ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9231', '����ջ���������ԹԨ����ä (DIAGTYPE) = 1-5', '��Ǩ�ͺ DIAGTYPE or DXTYPE  = 1-5', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9232a', '�������ä���������˭ԧ (���ʹ����ǹ��ҧ���� DX9232 ��ҹ��)', '��Ǩ�ͺ�����ä����ʹ���ͧ�����ѡ�����������ä (��� Appendix A3- A4 �ͧ DRG 4.0)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9232b', '�������ä��������Ȫ��  (���ʹ����ǹ��ҧ���� DX9232 ��ҹ��)', '��Ǩ�ͺ�����ä����ʹ���ͧ�����ѡ�����������ä (��� Appendix A3- A4 �ͧ DRG 4.0) ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9232c', '��������ʹ���ͧ�Ѻ���ط���˹������������ä���  (���ʹ����ǹ��ҧ���� DX9232 ��ҹ��)', '��Ǩ�ͺ�����ä����ʹ���ͧ�����ѡ�����������ä (��� Appendix A3- A4 �ͧ DRG 4.0) DX9232d', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3103', '������ PID �繤����ҧ', '�������� PID ����繤�������ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3105', 'DATE_SERV ���������ٻ�ͧ YYYYMMDD ��л�����繤.�.', '�����������������ٻ YYYYMMDD ��лյ�ͧ�繤.�.', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3142', '������ ANCRES  �դ�������ҡѺ 1 ���� 2', '�������� ANCRES ����դ����ҡѺ 1 ���� 2', '���� �������  > ��ͺ���� > ᶺ �ҡ����� > ����Ѻ��ԡ�ýҡ����� >  ����������');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1105', '������ DATE_SERV ���������ٻ�ͧ YYYYMMDD ��� YYYY ����繤.�.', '�������� DATE_SERV ���������ٻ YYYYMMDD ��� YYYY �繤.�.', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1130', '������ DIAGCODE �դ����ҧ (null)', '�������� DIAGCODE �������繤����ҧ (not null)', 'ᶺ����ԹԨ��� > ���ŧ���� ICD-10   >  ���� ICD10');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3101', '������ PCUCODE �����ú 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3104', '������ SEQ �繤����ҧ (null)', '�������� SEQ ����繤�������ҧ (not null)', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3105', 'DATE_SERV ���������ٻ�ͧ YYYYMMDD ��л�����繤.�.', '��������������ٻ YYYYMMDD ��лյ�ͧ�繤.�.', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9200', '�������ö������§�����źؤ���� (�����źؤ�ŵ�ͧ��ҹ��õ�Ǩ�ͺ)', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9201', '���������˹��º�ԡ������� ʹ�. (PCUCODE)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9205', '�������������㹻է�����ҳ', '��Ǩ�ͺ�������ѹ�������ԡ�� (DATE_SERV)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9230', '����ա����������Ѥ�չ��������ҵðҹ ʹ�.', '��Ǩ�ͺ�����Ѥ�չ��������ҵðҹ ʹ�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP9299', '�����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3103', '������ PID �繤����ҧ', '�������� PID ����繤�������ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9200', '�������ö������§�����źؤ���� (�����źؤ�ŵ�ͧ��ҹ��õ�Ǩ�ͺ)', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9201', '���������˹��º�ԡ������������ ʹ�. (PCUCODE)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9205', '�������������㹻է�����ҳ', '��Ǩ�ͺ�������ѹ�������ԡ�� (DATE_SERV)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9207', '�������˭ԧ  (�����Ҩ�������ö������§������� PERSON ��)', '��������������˭ԧ ���� ��Ǩ�ͺ���������� PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9208', '������Ȫ�� (�����Ҩ�������ö������§������� PERSON ��)', '�������������Ȫ�� ���� ��Ǩ�ͺ���������� PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9240', '����������������ҧ 9-60 ��', '�����������������ҧ 9 - 60 ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP9299', '�բ����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3103', '������ PID �繤����ҧ', '�������� PID ����繤�������ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3130', '������ FPTYPE �դ�ҷ������� 1-7', '�������� DIAGTYPE ����դ�� 1-7', '����������� > ��ͺ���� >ᶺ�ҧἹ��ͺ����  > �Ըա�ä�����Դ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3101', '������ PCUCODE �����ú 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3144', '������ LMP ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� > ᶺ�ҡ����� > �ѹ�á�ͧ����ջ�Ш���͹�����ش����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9200', '�������ö������§�����źؤ���� (�����źؤ�ŵ�ͧ��ҹ��õ�Ǩ�ͺ)', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9201', '���������˹��º�ԡ������������ ʹ�. (PCUCODE)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9205', '�����š�ô�����������ѧ�ش�������㹻է�����ҳ', '��Ǩ�ͺ�����š�ô�����������ѧ�ش', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9207', '�������˭ԧ  (�����Ҩ�������ö������§������� PERSON ��)', '��������������˭ԧ ���� ��Ǩ�ͺ���������� PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9240', '���������������ҧ 9-60 ��', '�к����������ҧ 9-60 ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9241', '������ѹ�����������駷�� 1 (PPCARE1)', '��Ǩ�ͺ�ѹ�����������駷�� 1', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9242', '�ѹ�������硤�����ѧ����ҡ���Ҥ����á', '��Ǩ�ͺ�ѹ�������������Ф���', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC9299', '�բ����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9200', '�������ö������§�����źؤ���� (�����źؤ�ŵ�ͧ��ҹ��õ�Ǩ�ͺ)', '��Ǩ�ͺ�����źؤ��㹵��ҧ PERSON ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9201', '���������˹��º�ԡ������������ ʹ�. (PCUCODE)', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9202', '���������˹��º�ԡ������� ʹ�.', '��Ǩ�ͺ�������  PCUCODE ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9205', '�������������㹻է�����ҳ����˹�', '��Ǩ�ͺ�ѹ�������ԡ�� (DATE_SERV)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9206', '���ʤ�Թԡ (CLINIC) ���١��ͧ', '��Ǩ�ͺ�����Ť�Թԡ (CLINIC)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1106', '������ CLINIC �դ����ҧ (null) �������ú 5 ��ѡ', '�������� CLINIC �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1140', '������ SERVICE_TYPE ����� 1 ���� 2', '�������� SERVICE_TYPE ����դ�� 1 ���� 2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9230', '����բ����š���ԹԨ����ä���١��ͧ', '��Ǩ�ͺ���������ʡ���ԹԨ����ä���١��ͧ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('OP9299', '�����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1103', '������ PID �դ����ҧ (null)', '�������� PID�������繤����ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE9203', '�� PID ��ӫ�͹', '��Ǩ�ͺ������ PID ��������ա�ë�ӫ�͹�������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE9200', '�������ö������§�Ѻ����������ԡ����', '��Ǩ�ͺ����������§�Ѻ����������ԡ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1107', '������ SEX  �դ�ҷ������� 1 ���� 2', '�������� SEX  ����դ�� 1 ���� 2', 'ᶺ�����Ż�Ъҡ� > ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1109', '������ BIRTH ���������ٻ�ͧ YYYYMMDD ��� YYYY ����� �.�.', '�������� BIRTH ���������ٻ YYYYMMDD ��� YYYY �� �.�.', 'ᶺ�����Ż�Ъҡ� > �ѹ�Դ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1103', '������ PID �դ����ҧ (null)', '�������� PID �������繤����ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX9200', '�������ö������§�������ԡ����', '��Ǩ�ͺ�����š������ԡ���������ö������§�������ԡ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX9230', '����������ѵ����� ICD9CM ���� ICD10TM ����ᾷ��Ἱ��', '��Ǩ�ͺ�����ѵ���������  ICD9CM ���� ICD10TM ����ᾷ��Ἱ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX9299', '�բ����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3109', '������ BIRTH ���������ٻ�ͧ YYYYMMDD ��� YYYY ����� �.�.', '�������� BIRTH ���������ٻ YYYYMMDD ��� YYYY �� �.�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3110', '������ MSTATUS �դ�ҷ������� 1-6 ���� 9', '����Ң����� MSTATUS ����դ�� 1-6 ���� 9', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3111', '������ OCCUPA �����ú 3 ��ѡ', '�������� OCCUPA ���ú 3 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3112', '������ NATION �����ú 3 ��ѡ', '�������� NATION ���ú 3 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE9202', '�� CID ��ӫ�͹', '��Ǩ�ͺ������ CID ��������ա�ë�ӫ�͹�������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3103', '������ PID �դ����ҧ (null)', '�������� PID�������繤����ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3107', '������ SEX  �դ�ҷ������� 1 ���� 2', '�������� SEX  ����դ�� 1 ���� 2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RF9225', '����ʶҹ��Һ�ŷ���������� ʹ�.(REFERINHOS)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (REFERINHOS)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RF9226', '����ʶҹ��Һ�ŷ���Ѻ�觵�������� ʹ�.(REFEROUHOS)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (REFEROUHOS)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3102', '������ CID ����� 13 ��ѡ', '�������� CID ����� 13 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1103', '������ PID �դ����ҧ (null)', '�������� PID �������繤����ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX9200', '�������ö������§�������ԡ����', '��Ǩ�ͺ�����š������ԡ���������ö������§�������ԡ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3131', '������ APLACE �繤����ҧ (null)', '�������� ACPLACE ����繤�������ҧ (not null)', 'Set up > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3131', '������ FPPLACE �繤����ҧ', '�������� FPPLACE ����繤�������ҧ (not null)', 'Set up > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1106', '������ CLINIC �դ����ҧ (null) �������ú 5 ��ѡ', '�������� CLINIC �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', 'ᶺ����ԹԨ���  > ���ŧ���� ICD-10  > �������ä');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set up > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX9230', '��������͡����������Ҷ١��ͧ����ҵðҹ 24 ��ѡ (DIDSTD)', '��Ǩ�ͺ�������ҵðҹ 24 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX9299', '�����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1103', '������ PID �դ����ҧ (null)', '�������� PID �������繤����ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3101', '������ PCUCODE �����ú 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3102', '������ CID �����ú 13 ��ѡ', '�������� CID  ���ú 13 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3112', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3130', '������ FTYPE �դ�ҷ������� 0-7', '�������� FTYPE ����դ�� 0-7', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM3143', '������ NUMSON �繤����ҧ (null)', '�������� NUMSON ����繤�������ҧ (not null)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9200', '�������ö������§�����źؤ����', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9201', '���������˹��º�ԡ������������ ʹ�. (PCUCODE)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9202', '������Ţ��ЪҪ�㹰ҹ����¹��ɮ��', '�к��Ţ��ЪҪ�㹰ҹ����¹��ɮ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9207', '�������˭ԧ  (�����Ҩ�������ö������§������� PERSON ��)', '��������������˭ԧ ���� ��Ǩ�ͺ���������� PERSON', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9240', '���������������ҧ 9-60 ��', '�к����������ҧ 9-60 ��', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9241', '��������˵ء����������Դ', '�к����ʡ�ä�����Դ����դ�� 1 ���� 2 ���� 3', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9242', '�������������㹻է�����ҳ 2553', '�кآ������������㹻է�����ҳ 2553', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('WM9299', '�բ����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE3140', '������ TYPEAREA �դ�ҷ������� 0-4', '����Ң�����TYPEAREA ����դ�� 0-4', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX9299', '�բ����š����������ԹԨ����ä��ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3102', '������ CID �����ú 13 ��ѡ', '�������� CID  ���ú 13 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9200', '�������ö������§�����źؤ���� (�����źؤ�ŵ�ͧ��ҹ��õ�Ǩ�ͺ)', '��Ǩ�ͺ�����š������ԡ���������ö������§�Ѻ�����źؤ�����������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9201', '���������˹��º�ԡ������������ ʹ�. (PCUCODE)', '��Ǩ�ͺ�����١��ͧ�ͧ����˹��º�ԡ�÷��������� ʹ�. (PCUCODE)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9205', '�����š�ô����硤�����ѧ�ش�������㹻է�����ҳ', '��Ǩ�ͺ�����š�ô����硤�����ѧ�ش', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9240', '�����Ź��˹ѡ (BWEIGHT) �դ�ҹ��¡��� 500 ���� ', '�������Ź��˹ѡ (BWEIGHT) ����դ���ҡ���� 500 ����', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9241', '������ѹ������g�硤��駷�� 1 (BCARE1)', '��Ǩ�ͺ�ѹ�������硤��駷�� 1', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9242', '�ѹ�������硤�����ѧ����ҡ���Ҥ����á', '��Ǩ�ͺ�ѹ�������١����Ф���', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP9299', '�բ����ū�ӫ�͹', '��Ǩ�ͺ������ӫ�͹�ͧ������', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3103', '����� PID', '��Ǩ�ͺ������ PID', '');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3104', '����բ����� SEQ �����繤����ҧ', '�������� SEQ  ����繤�������ҧ (not null)', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3141', '������ GA �繤����ҧ', '�������� GA  ����繤�������ҧ (not null)', '���� �������  > ��ͺ���� > ᶺ �ҡ�����  ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3144', '������ GRAVIDA ��������Թ', '�������� GRAVIDA ����Թ 2 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3145', '������ ANCNO ��������Թ', '�������� ANCNO ����Թ 1 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1104', '������ SEQ �դ����ҧ (null)', '�������� SEQ �������繤����ҧ (not null)', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1131', '������ DIAGTYPE �դ�ҷ������� 1-5', '�������� DIAGTYPE ����դ�� 1-5', 'ᶺ����ԹԨ��� > ���ŧ���� ICD-10   >  ���� ICD10');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1106', '������ CLINIC �դ����ҧ (null) �������ú 5 ��ѡ', '�������� CLINIC �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', 'ᶺ����ԹԨ���  >���ŧ���� ICD-10   >  �������ä');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3131', '������ VCCPLACE �繤����ҧ (null)', '�������� VCCPLACE ����繤�������ҧ (not null)', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����)');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3101', '������ PCUCODE �����ú 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3104', '������ SEQ �繤����ҧ (null)', '�������� SEQ ����繤�������ҧ (not null)', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3105', 'DATE_SERV ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '��������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3143', '������ SEQ �繤����ҧ (null)', '�������� SEQ ����繤�������ҧ (not null)', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3157', '������ PPCARE1 ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�������� ���������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > ���������ѧ��ʹ ���駷�� 1');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1130', '������ PROCED �դ����ҧ (null)', '�������� PROCED �������繤����ҧ (not null)', 'ᶺ����ԹԨ���  > ���ŧ���� ICD-9  > ���� ICD9');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3130', '������ VCCTYPE �繤����ҧ  (null)', '�������� VCCTYPE ����繤�������ҧ (not null)', 'Set up  > �������  > ��¡���Ѥ�չ >  ��������Ѻ�͡��§ҹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3158', '������ PPCARE2 ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�������� ���������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > ���������ѧ��ʹ ���駷�� 2');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3159', '������ PPCARE3 ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > ���������ѧ��ʹ ���駷�� 3');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3160', '������ PPRES �դ�ҷ�������ҡѺ 1 ���� 2', '�������� PPRES ����դ����ҡѺ 1 ���� 2', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > �š�õ�Ǩ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3145', '������ EDC ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� > ᶺ�ҡ����� > �ѹ����˹���ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3146', '������ VDRL_RS �դ������� 1-2 ��� 8-9', '�������� VDRL_RS ����դ�� 1-2 ��� 8-9', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > �� VDRL');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3147', '������ HB_RS �դ������� 1-2 ��� 8-9', '�������� HB_RS ����դ�� 1-2 ��� 8-9', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > �� HB');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3148', '������ THALASS �դ������� 1-2 ��� 8-9', '��������THALASS ����դ�� 1-2 ��� 8-9', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > �� THALASS');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3149', '������ DEATAL �դ������� 0 ���� 1', '�������� DEATAL ����դ�� 0 ��� 1', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > ��Ǩ�آ�Ҿ�ѹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3150', '������ TCARIES �繤����ҧ (null)', '�������� TCARIES ����繤�������ҧ (not null)', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > �ѹ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3151', '������ TARTAR �դ������� 0 ���� 1 ���� 8 ', '�������� TARTAR ����դ�� 0 ���� 1 ���� 8', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� >���Թ������');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3152', '������ GUMINF �դ������� 0 ���� 1 ���� 8', '�������� GUMINF ����դ�� 0 ���� 1 ���� 8', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > �˧�͡�ѡ�ʺ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3153', '������ BDATE ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�������� ���������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ >�ѹ����ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3154', '������ BPLACE �դ�ҷ������� 1-5 ', '�������� BPLACE ����դ�� 1-5 ', '����������� > ��ͺ���� > ᶺ �����š�ä�ʹ >ʶҹ����ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3156', '������ BDOCTOR �դ�ҷ������� 1-5', '�������� BDOCTOR ����դ�� 1-5', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > ���������Ӥ�ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1110', '������ MSTATUS �դ�ҷ������� 1-6 ���� 9', '����Ң����� MSTATUS ����դ�� 1-6 ���� 9', 'ᶺ�����Ż�Ъҡ� > ʶҹ�����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1111', '������ OCCUPA �����ú 3 ��ѡ', '�������� OCCUPA ���ú 3 ��ѡ', 'ᶺ�����Ż�Ъҡ� >  �Ҫվ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1112', '������ NATION �����ú 3 ��ѡ', '�������� NATION ���ú 3 ��ѡ', 'ᶺ�����Ż�Ъҡ� > �ѭ�ҵ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1140', '������ TYPEAREA �դ�ҷ������� 0-4', '����Ң�����TYPEAREA ����դ�� 0-4', 'ᶺ�����Ż�Ъҡ� > ʶҹкؤ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1104', '������ SEQ �դ����ҧ (null)', '�������� SEQ �������繤����ҧ (not null)', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1105', '������ DATE_SERV ���������ٻ�ͧ YYYYMMDD ��� YYYY ����繤.�.', '�������� DATE_SERV ���������ٻ YYYYMMDD ��� YYYY �繤.�.', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1106', '������ CLINIC �դ����ҧ (null) �������ú 5 ��ѡ', '�������� CLINIC �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', 'ᶺ����ԹԨ���  > ���ŧ���� ICD-9  >  �������ä');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1104', '������ SEQ �դ����ҧ (null)', '�������� SEQ �������繤����ҧ (not null)', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1105', '������ DATE_SERV ���������ٻ�ͧ YYYYMMDD ��� YYYY ����繤.�.', '�������� DATE_SERV ���������ٻ YYYYMMDD ��� YYYY �繤.�.', 'ᶺ����Ѻ��ԡ��  > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1120', '������ DRUGPRICE �դ����ҧ (null) ��������繷ȹ��� 2 ���˹�', '�������� DRUGPRICE �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', 'Set up > HospitalOS Setup  > ��¡�õ�Ǩ�ѡ��  > ᶺ�Ҥ� >  �ҤҢ�� (�ҷ)');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1121', '������ DRUGCOST �դ����ҧ (null) ��������繷ȹ��� 2 ���˹�', '�������� DRUGCOST �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', 'Set up > HospitalOS Setup   > ��¡�õ�Ǩ�ѡ��  > ᶺ�Ҥ�  >  �Ҥҷع (�ҷ)');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1130', '������ DIDSTD �����ú 24 ��ѡ', '�������� DIDSTD ���ú 24 ��ѡ', 'Set up > HospitalOS Setup  > ��§ҹ 18/2553  > �Ѻ����� 24 ��ѡ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1131', '������ AMOUNT �դ����ҧ (null)', '�������� AMOUNT �������繤����ҧ (not null)', 'ᶺ��¡�õ�Ǩ / �ѡ�� > ����ҳ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1101', '������ PCUCODE ����� 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1104', '������ SEQ �դ����ҧ (null)', '�������� SEQ �������繤����ҧ (not null)', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1105', '������ DATE_SERV ���������ٻ�ͧ YYYYMMDD ��� YYYY ����繤.�.', '�������� DATE_SERV ���������ٻ YYYYMMDD ��� YYYY �繤.�.', 'ᶺ����Ѻ��ԡ�� > ���� visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1120', '������ PRICE �դ����ҧ (null) ��������繷ȹ��� 2 ���˹�', '�������� PRICE �������繤����ҧ (not null) ���͵�Ǩ�ͺ����繷ȹ��� 2 ���˹�', 'ᶺ����Թ ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1121', '������ PAY �դ����ҧ (null) ��������繷ȹ��� 2 ���˹�', '�������� PAY �������繤����ҧ (not null)', 'ᶺ����Թ ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1125', '������ REFERIN  �դ�ҷ������� 0 ���� 1', '�������� REFERIN ����դ�� 0 ���� 1', '��������ͧ���  > ��� refer ������ > Refer In');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1126', '������ REFINHOS �����ú 5 ��ѡ', '�������� REFINHOS ���ú 5 ��ѡ', '��������ͧ���  > ��� refer ������ > ʶҹ��Һ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1127', '������ REFEROUT  �դ�ҷ������� 0 ���� 1', '�������� REFEROUT ����դ�� 0 ���� 1', '��������ͧ��� > ��� refer ������  > Refer Out');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1128', '������ REFOUHOS �����ú 5 ��ѡ', '�������� REFOUHOS ���ú 5 ��ѡ', '��������ͧ���  > ��� refer ������  > ʶҹ��Һ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3101', '������ PCUCODE �����ú 5 ��ѡ', '��Ǩ�ͺ������ PCUCODE ��ͧ�� 5 ��ѡ', 'Set Up  > HospitalOS Setup  > ��¡������ >  ʶҹ��Һ�ŷ��Դ��� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3143', '������ GRAVIDA �繤����ҧ (null)', '�������� GRAVIDA ����繤�������ҧ (not null)', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > �������');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3144', '������ BPLACE �դ�ҷ������� 1-5 ', '�������� BPLACE ����դ�� 1-5 ', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ >ʶҹ����ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3145', '������ BTYPE �դ�ҷ������� 1-5', '�������� BTYPE ����դ�� 1-5', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ > �Ըա�ä�ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3146', '������ BDOCTOR �դ�ҷ������� 1-5', '�������� BDOCTOR ����դ��1-5', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ > �������ͧ���Ӥ�ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3148', '������ BCARE1 ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ > �����١���駷�� 1 ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3149', '������ BCARE2 ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ > �����١���駷�� 2');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3150', '������ BCARE3 ���������ٻ�ͧ YYYYMMDD ��л��Դ����繤.�.', '�����������������ٻ YYYYMMDD ��л��Դ��ͧ�繤.�.', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ > �����١���駷�� 3');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3151', '������ BCRES �դ�ҷ������� 1 ���� 2', '�������� BCRES ����դ�� 1 ���� 2', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ > �š�õ�Ǩ��á��ѧ��ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3140', '������ BWEIGHT �繤����ҧ (null)', '�������� BWEIGHT ����繤�������ҧ (not null)', '����������� > ��ͺ���� >ᶺ�����š�ä�ʹ >���˹ѡ�á��ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1141', '������ PRENAME ����� 20 ��ѡ', '�������� PRENAME ���ú 20 ��ѡ', 'ᶺ�����Ż�Ъҡ� > �ӹ�˹��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1142', '������ FSTATUS �դ�ҷ������� 1 ���� 2 ', '�������� FSTATUS ����դ�� 1 ���� 2', 'ᶺ�����Ż�Ъҡ� > ���� FM');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1143', '������ FATHER ����� 13 ��ѡ', '����� FATHER ���ú 13 ��ѡ', 'ᶺ�����Ż�Ъҡ� > ���ͺԴ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1144', '������ MOTHER ����� 13 ��ѡ', '�������� MOTHER ���ú 13 ��ѡ', 'ᶺ�����Ż�Ъҡ� > ��ô�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1145', '������ COUPLE ����� 13 ��ѡ', '�������� COUPLE ���ú 13 ��ѡ', 'ᶺ�����Ż�Ъҡ� > �������');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1146', '������ MOVEIN ���������ٻẺ YYYYMMDD ��л������ �.�.', '�����������������ٻ YYYYMMDD ��лյ�ͧ�� �.�.', 'ᶺ�����Ż�Ъҡ� > ���� FM');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1147', '������ BGROUP �դ�ҷ������� 1-4', '�������� BGOUP ����դ�� 1-4', 'ᶺ�����Ż�Ъҡ� > �������ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1148', '������ HOUSE ����� 75 ��ѡ', '�������� HOUSE ���ú 75 ��ѡ', 'ᶺ�����Ż�Ъҡ� > ��ҹ�Ţ���');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1149', '������ VILLAGE ����� 2 ��ѡ', '�������� VILLAGE ���ú 2 ��ѡ', 'ᶺ�����Ż�Ъҡ� > ������');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1141', '������ LOCATE �դ�ҷ������� 1 ���� 2', '�������� LOCATE ����դ�� 1 ���� 2', '����Ѻ��ԡ��  > ������ visit');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1142', '������ PTTYPE �դ�ҷ������� 0-2', '�������� PTTYPE ����դ�� 0-2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1143', '������ INTIME �դ�ҷ������� 1 ���� 2', '�������� INTIME ����դ�� 1 ���� 2', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1144', '������ INSTYPE ����� 2 ��ѡ', '�������� INSTYPE ���ú 2 ��ѡ', '����Ѻ��ԡ�� > �����š���Ѻ��ԡ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1151', '������ VHID ����� 8 ��ѡ', '�������� VHID ���ú 8 ��ѡ', '����������� > �����������ҹ > ���������ҹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1145', '������ INSID ����� 18 ��ѡ', '�������� INSID ���ú 18 ��ѡ', '����Ѻ��ԡ�� > �Ţ���ѵ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1146', '������ MAIN ����� 5 ��ѡ', '�������� MAIN ���ú 5 ��ѡ', 'ᶺ����Ѻ��ԡ�� > ʶҹ��Һ����ѡ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1148', '������ COST �Ҥ������ͧ�ҡ���� 0 ������Ţ�ȹ�����ѧ�ش 2 ���˹�', '�������� COST �ҡ���� 0 ����Ţ�ȹ�����ѧ�ش�� 2 ���˹�', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('DX1132', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDDHHMMSS ��е�ͧ�繻� �.�.', 'ᶺ����ԹԨ��� > �����ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('SE1147', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻ YYYYMMDDHHMMSS ��л��� �.�.', 'ᶺ����Ѻ��ԡ�� >�����ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1131', '������ SERVPRIC �����ȹ��� 2 ���˹� ����դ�ҹ��¡��� 0', '������������繷ȹ��� 2 ���˹�����դ���ҡ���� 0', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PX1132', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻ YYYYMMDDHHMMSS ��лյ�ͧ�� �.�.', 'ᶺ����ԹԨ��� >���ŧ icd9>�������ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3106', '������ DID ����� 24 ��ѡ', '�������� DID ���ú 24 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3107', '������ AMOUNT �繤����ҧ', '�������� AMOUNT �������繤����ҧ �ҡ�������� 0', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('FP3108', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDDHHMMSS ��е�ͧ�繻� �.�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('EP3132', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDDHHMMSS ��е�ͧ�繻� �.�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3152', '������ PID �դ����ҧ', '�������� PID �������繤����ҧ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3153', '������ MPID �դ����ҧ', '�������� MPID �������繤����ҧ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3154', '������ BDATE ���������ٻ�ͧ YYYYMMDD ��л������ �.�.', '�����������������ٻẺ YYYYMMDD ��е�ͧ�繻� �.�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1102', '������ CID ����� 13 ��ѡ', '�������� CID ����� 13 ��ѡ', 'ᶺ�����Ż�Ъҡ� > �Ţ�ѵû�ЪҪ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3156', '������ BTYPE ������� 1-5', '�������� BTYPE ����դ�� 1-5', '������� > ��ͺ���� > �����š�ä�ʹ > �Ըա�ä�ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3157', '������ ASPHYXIA ������� 0 ���� 1', '�������� ASPHYXIA ����դ�� 0 ���� 1', '������� > ��ͺ���� > �������硷�á > ���ТҴ�͡��ਹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3158', '-������ VITK ������� 0 ���� 1', '�������� VITK ����դ�� 0 ���� 1', '������� > ��ͺ���� > �������硷�á > �Ѻ Vit K');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3161', '������ GRAVIDA �繤����ҧ', '�������� GRAVIDA ����繤����ҧ', '����������� > ��ͺ���� > ᶺ�ҡ����� > �������');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3162', '������ HIV_RS �դ������� 1-2 ���� 8-9', '�������� HIV_RS ����դ�� 1-2 ���� 8-9', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� > �� HIV');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3164', '������ HCT_RS �繤����ҧ ', '�������� HCT_RS ����繤����ҧ', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� >�� HCT');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3165', '������ BRESULT ����� 6 ��ѡ', '�������� BRESULT ���ú 6 ��ѡ', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > ������ش��õ�駤����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3166', '������ BHOSP ����� 5 ��ѡ', '�������� BHOSP ���ú 5 ��ѡ', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > ʶҹ����ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3167', '������ LBORN �繤����ҧ', '�������� LBORN ����繤����ҧ (�������� 0)', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > �ӹǹ���Դ�ժվ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3155', '������ BHOSP ����� 5 ��ѡ', '�������� BHOSP ���ú 5 ��ѡ', '����������� > ��ͺ���� > ��������ѧ��ʹ >ʶҹ����ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3163', '������ DATEHCT ���������ٻ�ͧ YYYYMMDD ��л������ �.�.', '�����������������ٻẺ YYYYMMDD ��е�ͧ�繻� �.�.', '����������� > ��ͺ���� > ᶺ�ҡ����� > ����Ѻ��ýҡ����� >�ѹ����Ǩ HCT');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3155', '������ BTYPE �դ�ҷ������� 1-6', '�������� BTYPE ����դ�� 1-6', ' ����������� > ��ͺ���� > ᶺ�����š�ä�ʹ >�Ըա�ä�ʹ/����ش��õ�駤���� ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3168', '������ SBORN �繤����ҧ', '�������� SBORN ����繤����ҧ(�������� 0)', '����������� > ��ͺ���� > ᶺ�����š�ä�ʹ > �ӹǹ�硵��㹡�ä�ʹ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1132', '������ DID ���ú 30 ��ѡ', '�������� DID ���ú 30 ��ѡ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1133', '������ DNAME ����� �����������ѹ��Ѻ DID', '�������� DNAME �������ѹ��Ѻ DID', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1134', '������ UNIT �繤����ҧ', '�������� UNIT �������繤����ҧ', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1135', '������ UNIT_PACKING �繤����ҧ', '�������� UNIT_PACKING �������繤����ҧ(�繤�� Null ��)', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('RX1136', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDDHHMMSS ��е�ͧ�繻� �.�.', NULL);
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1150', '������ HN ����� 15 ��ѡ', '�������� HN ���ú 15 ��ѡ', 'ᶺ�����Ż�Ъҡ� > �����ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PP3159', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDDHHMMSS ��е�ͧ�繻� �.�.', '����������� > ��ͺ���� > ��������ѧ��ʹ >�����ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('AN3143', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDDHHMMSS ��е�ͧ�繻� �.�.', '����������� > ��ͺ���� > ᶺ�ҡ����� >����Ѻ��ԡ�ýҡ�����>�����ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('MC3169', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��л������ �.�.', '�����������������ٻẺ YYYYMMDD ��е�ͧ�繻� �.�.', '����������� > ��ͺ���� > ��������ѧ��ʹ >�����ѹ�֡');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1152', '������ HID ����� 14 ��ѡ', '�������� HID ���ú 14 ��ѡ', '');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1153', '������ NAME �繤����ҧ', '�������� NAME ���������ҧ', 'ᶺ�����Ż�Ъҡ� > ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1154', '������ LNAME �繤����ҧ', '�������� LNAME ���������ҧ', 'ᶺ�����Ż�Ъҡ� >���ʡ��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1156', '������ TAMBON ����� 2 ��ѡ', '�������� TAMBON ���ú 2 ��ѡ', 'ᶺ�����Ż�Ъҡ� > �Ӻ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1157', '������ AMPUR ����� 2 ��ѡ', '�������� AMPUR ���ú 2 ��ѡ', 'ᶺ�����Ż�Ъҡ� >�����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1158', '������ CHANGWAT ����� 2 ��ѡ', '�������� CHANGWAT ���ú 2 ��ѡ', 'ᶺ�����Ż�Ъҡ� >�ѧ��Ѵ');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1159', '������ RACE ����� 3 ��ѡ', '�������� RACE ���ú 3 ��ѡ', 'ᶺ�����Ż�Ъҡ� >���ͪҵ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1160', '������ RELIGION ����� 1 ��ѡ', '�������� RELIGION ���ú 1 ��ѡ', 'ᶺ�����Ż�Ъҡ� >��ʹ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1162', '������ EDUCATE ����� 7 ��ѡ', '�������� EDUCATE ���ú 7 ��ѡ', 'ᶺ�����Ż�Ъҡ� >����֡��');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1163', '������ DISCHAR ������� 1-3 ���� 9', '�������� DISCHAR �繤�� 1-3 ���� 9', 'ᶺ�����Ż�Ъҡ� > ������ FM >ʶҹ�');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1164', '������ DDISCH ���������ٻ�ͧ YYYYMMDD ��� YYYY ����� �.�.', '��������������ٻ�ͧ YYYYMMDD ��� YYYY �� �.�.', 'ᶺ�����Ż�Ъҡ� >������ FM >�ѹ����˹���');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1165', '������ LABOR ����� 2 ��ѡ', '�������� LABOR ���ú 2 ��ѡ', 'ᶺ�����Ż�Ъҡ� >����������ҧ����');
INSERT INTO public.b_oppp54_errcode(code_id, description, remark, hint)
  VALUES('PE1166', '������ D_UPDATE ���������ٻ�ͧ YYYYMMDDHHMMSS ��� YYYY ����� �.�.', '��������������ٻ�ͧ YYYYMMDDHHMMSS ��� YYYY �� �.�.', 'ᶺ�����Ż�Ъҡ� >�������ѹ');;

-- create s_oppp54_version
CREATE TABLE s_oppp54_version (
    version_id            varchar(255) NOT NULL,
    version_number            	varchar(255) NULL,
    version_description       	varchar(255) NULL,
    version_application_number	varchar(255) NULL,
    version_database_number   	varchar(255) NULL,
    version_update_time       	varchar(255) NULL 
    );

ALTER TABLE "s_oppp54_version"
	ADD CONSTRAINT "s_oppp54_version_pkey"
	PRIMARY KEY ("version_id");


INSERT INTO s_oppp54_version VALUES ('9770000000001', '1', 'OPPP 54 Module', '1.0.140111', '1.0.140111', (select current_date) || ','|| (select current_time));

INSERT INTO s_script_update_log values ('OPPP54_Module','update_oppp54_001.sql',(select current_date) || ','|| (select current_time),'Initialize OPPP 54 Module');

