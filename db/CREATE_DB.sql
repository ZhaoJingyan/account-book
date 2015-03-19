-- 长文本记录表，资源表
create table if not exists [text] (
  [id]       integer            -- 主键
    primary key
    autoincrement
 ,[text]     text               -- 内容
    not null
);

-- 科目
create table if not exists [account] (
  [id] integer                  -- 主键
    primary key
    autoincrement
 ,[name] varchar(10)            -- 科目名
    unique
    not null
 ,[is_accounting] integer       -- 可记账标记（0：不可记账，1：可记账）
    default 0
 ,[father]        integer       -- 父级科目
    default 0
 ,[depth] integer               -- 科目深度，科目等级
    not null
 ,[text_id] integer             -- 长文本
 ,[create_time] datetime        -- 创建时间
    not null
 ,foreign key([text_id]) references [text](id)
);

-- 账本
create table if not exists [book] (
  [id] integer                  -- 主键
    primary key
    autoincrement
 ,[account_id] integer          -- 科目外键
    not null
 ,[transaction_id] integer      -- 交易ID
    not null
 ,[water] integer               -- 流水标记，流水账序号
    not null
 ,[symbol] char                 -- 借贷标记
    not null
 ,[money] real                  -- 金额
    not null
 ,[text_id] integer             -- 长文本
 ,[accounting_time] datetime    -- 发生时间
 ,[create_time] datetime        -- 创建时间
    not null
 ,foreign key([account_id]) references [account](id)
 ,foreign key([text_id]) references [text](id)
);

-- 流水账抬头
create table if not exists [journal](
  [id]
)

