create definer = root@localhost trigger trigger_addorder
    after INSERT
    on `order`
    for each row
begin
    declare fmoney double;
    declare status VARCHAR(20) character set utf8;
    declare ftype VARCHAR(20) character set utf8;
    declare user_id VARCHAR(20) character set utf8;
    declare order_id int;

    set status=NEW.status;
    if (status = '已付定金')
        then
        set ftype ='付定金' ;
        set fmoney = NEW.deposit;
    else
        set ftype ='付全款' ;
        set fmoney = NEW.total_payment;
    end if;

    set order_id=NEW.id;
    set user_id=NEW.client_id;

    insert into finance(`money`,`type`,`order_id`,`user_id`) values(fmoney,ftype,order_id,user_id);
end