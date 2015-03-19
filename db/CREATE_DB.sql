-- ���ı���¼����Դ��
create table if not exists [text] (
  [id]       integer            -- ����
    primary key
    autoincrement
 ,[text]     text               -- ����
    not null
);

-- ��Ŀ
create table if not exists [account] (
  [id] integer                  -- ����
    primary key
    autoincrement
 ,[name] varchar(10)            -- ��Ŀ��
    unique
    not null
 ,[is_accounting] integer       -- �ɼ��˱�ǣ�0�����ɼ��ˣ�1���ɼ��ˣ�
    default 0
 ,[father]        integer       -- ������Ŀ
    default 0
 ,[depth] integer               -- ��Ŀ��ȣ���Ŀ�ȼ�
    not null
 ,[text_id] integer             -- ���ı�
 ,[create_time] datetime        -- ����ʱ��
    not null
 ,foreign key([text_id]) references [text](id)
);

-- �˱�
create table if not exists [book] (
  [id] integer                  -- ����
    primary key
    autoincrement
 ,[account_id] integer          -- ��Ŀ���
    not null
 ,[transaction_id] integer      -- ����ID
    not null
 ,[water] integer               -- ��ˮ��ǣ���ˮ�����
    not null
 ,[symbol] char                 -- ������
    not null
 ,[money] real                  -- ���
    not null
 ,[text_id] integer             -- ���ı�
 ,[accounting_time] datetime    -- ����ʱ��
 ,[create_time] datetime        -- ����ʱ��
    not null
 ,foreign key([account_id]) references [account](id)
 ,foreign key([text_id]) references [text](id)
);

-- ��ˮ��̧ͷ
create table if not exists [journal](
  [id]
)

