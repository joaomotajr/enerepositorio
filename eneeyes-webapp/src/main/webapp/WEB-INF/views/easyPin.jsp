<div ng-controller="easyPinController">
		<h1>jQuery easypin!</h1>
        <img src="{{imagem}}" width="1000" class="pin" easypin-id="example_image1" />

        <div class="easy-modal" style="display:none;" modal-position="free">
	        <form>
	            <h3>Selecione Dispositivo</h3>
	            <input type="text" class="form-control" name="content" placeholder="type">
	            <br>
	            <button type="button" class="btn btn-primary easy-submit">Salvar</button>
	        </form>
	    </div>        

        <div style="display:none;" width="130" shadow="true" popover>
            <div style="width:100%;text-align:center;">{[content]}</div>
        </div>

        <br />
        <input class="coords" type="button" value="Get coordinates!" />
</div>