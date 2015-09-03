<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.c2s.music.album.form.AlbumForm"%>
<%
	HashMap<String, AlbumForm> latestAlbums = (HashMap<String, AlbumForm>) request.getAttribute("latestAlbums");
	Set<String> latestKeySet = latestAlbums.keySet();
	HashMap<String, AlbumForm> mostViewedAlbums = (HashMap<String, AlbumForm>) request.getAttribute("mostViewedAlbums");
	Set<String> mostKeySet = mostViewedAlbums.keySet();
%>
<script type="text/javascript">
		function viewAlbum(id)
		{
			window.location.href = '<%=path%>/album.do?method=viewAlbum&albumid='+id+'&'+appendSessionId();
		}
	
		function addToPlayList(id) {
			var datasource = "<%=path%>/album.do?method=addAlbumToPlaylist&&ajax=y&albumid=" + id + "&" + appendSessionId();
			getDataReturnXml(datasource, addLiToPlyalist);
		}
	
</script>
<div class="strip" id="cont_bo_main_topsongs_strip_d"
	style="width: 800px;">
	<div class="strip" id="topsongs_striplabel_d" style="width: 800px;">
		Top Songs
	</div>
	<div class="div-table-row" style="width: 800px;">
		<%
			int i = 0;
			for (String key : latestKeySet)
			{
				AlbumForm albumForm = latestAlbums.get(key);
		%>
		<div class="div-table-col" id="r<%=i++%>"
			style="width: 140; height 200 px; border: dashed;">

			<img src="<%=albumForm.getAlbumFilePath()%>" class="albuum_thumb"
				onclick="viewAlbum('<%=albumForm.getId()%>');">
			<!-- alt="<%=albumForm.getAlbumFilePath()%>" -->
			<br>
			<%=albumForm.getAlbumTitle()%>
			<br>
			<input type="button"
				onclick="addToPlayList('<%=albumForm.getId()%>');"
				value="Add to Playlist">
		</div>
		<%
			if (i == 5)
				{
		%>
	</div>
	<div class="div-table-row" style="width: 800px;">
		<%
			}
			}
		%>
	</div>
</div>
<div class="strip" id="cont_bo_main_topsongs_strip_d"
	style="width: 800px;">
	<div class="strip" id="topsongs_striplabel_d" style="width: 800px;">
		Top album
	</div>
	<div class="div-table-row" style="width: 800px;">
		<%
			i = 0;
			for (String key : mostKeySet)
			{
				AlbumForm albumForm = mostViewedAlbums.get(key);
		%>
		<div class="div-table-col" id="r<%=i++%>"
			style="width: 140; height 150 px; border: threedshadow;">
			<img src="<%=albumForm.getAlbumFilePath()%>" class="albuum_thumb"
				onclick="viewAlbum('<%=albumForm.getId()%>');">
			<!--  alt="<%=albumForm.getAlbumFilePath()%>"-->
			<br>
			<%=albumForm.getAlbumTitle()%>
			<br>
			<input type="button"
				onclick="addToPlayList('<%=albumForm.getId()%>');"
				value="Add to Playlist">
		</div>
		<%
			if (i == 5)
				{
		%>
	</div>
	<div class="div-table-row" style="width: 800px;">
		<%
			}
			}
		%>
	</div>
</div>