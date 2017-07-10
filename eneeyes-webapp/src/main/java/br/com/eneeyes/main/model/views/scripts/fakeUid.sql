CREATE DEFINER=`root`@`localhost` FUNCTION `getFakeId`() RETURNS int(11)
    DETERMINISTIC
begin
return if(@fakeId, @fakeId:=@fakeId+1, @fakeId:=1);
end