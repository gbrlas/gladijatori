<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/WEB-INF/includes/navbar.jsp"%>
<fmt:setLocale value='en-US' />
<!DOCTYPE html>
<html lang="en">
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="${pageContext.request.contextPath}/js/sorttable.js"></script>
<script src="${pageContext.request.contextPath}/js/magnific-popup.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/changeCursor.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/spoiler.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/magnific-popup.css">

<script>
	$(document).ready(function() {
		$('.popup-with-form').magnificPopup({
			type : 'inline',
			preloader : false,
			focus : '#name',

			callbacks : {
				beforeOpen : function() {
					if ($(window).width() < 700) {
						this.st.focus = false;
					} else {
						this.st.focus = '#name';
					}
				}
			}
		});
		
		$('form').submit(function(e) {
			var mins = parseInt($("#vrijemedogmin").val(), 10);
			var secs = parseInt($("#vrijemedogsec").val(), 10);
			var time = mins*60 + secs;
			$('.error').remove();
			if (time < 1 || time > 3599) {
	            $('#submit').after('<span class="error" align="center" style="color: red;" > <br>Vrijeme mora biti iz intervala [00:01 - 59:59]</span>');
	            return false;
			}


			
			var url = '<%=request.getContextPath()%>' + "/servleti/sluzb/spremiDog";
			$.ajax({
				type : "POST",
				url : url,
				data : $("#form-dodajDog").serialize(), 
				success: function(){    
	                location.reload();   
	            }
			});
			
			e.preventDefault(); 
			 $.magnificPopup.close();
		});
	});
</script>

<style>
.buttonD {
	left: 15px;
}
</style>
</head>
<c:set var="vrstaKorisnika" scope="page" value="<%=vrstaKorisnika%>" />

<h1 align="center">
	<span class="hoverable"
		onclick="location.href='${pageContext.request.contextPath}/servleti/prikaziKlub?id=${utakmica.getDomacin().getId()}'">${utakmica.getDomacin().getIme()}</span>
	- <span class="hoverable"
		onclick="location.href='${pageContext.request.contextPath}/servleti/prikaziKlub?id=${utakmica.getGost().getId()}'">${utakmica.getGost().getIme()}</span>
</h1>

<br>
<div class="container" align="center" style="width: 50%">
	<br>
	<table class="table table-striped table-hover table-users"
		style="width: 100%">
		<thead>
			<tr>
				<th><h3 align="center">REZULTAT</h3></th>
				<c:if test="${utakmica.getMvp() != null}">
					<th><h3 align="center">MVP</h3></th>
				</c:if>
			</tr>
		</thead>
		<tbody align="center">
			<tr>
				<td class="hidden-phone"><h4>${utakmica.getRezultat()}</h4></td>
				<c:if test="${utakmica.getMvp() != null}">
					<td class="hidden-phone hoverable"
						onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${utakmica.getMvp().getId()}'"><h4>${utakmica.getMvp().getIme()}
							${utakmica.getMvp().getPrezime()}</h4></td>
				</c:if>
			</tr>
		</tbody>
	</table>

</div>
<br>
<br>
<div class="container" align="center" style="width: 100%;">
	<table class="table table-striped table-hover table-users">
		<thead>
			<tr>
				<th class="hidden-phone" align="center"><h3 align="center">
						<c:choose>
							<c:when test="${utakmica.isZavrsena()}">SAŽETAK</c:when>
							<c:otherwise>DOGAĐAJI</c:otherwise>
						</c:choose>
					</h3></th>
				<th class="hidden-phone" align="center"><h3 align="center">IGRAČI</h3></th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<div style="height: 650px; overflow-y: auto">
						<table
							class="table table-striped table-hover table-users sortable"
							style="width: 100%">
							<thead>
								<tr class="hoverable">
									<th class="hidden-phone sorttable-numeric">Vrijeme</th>
									<th class="hidden-phone">Ime igrača</th>
									<th class="hidden-phone">Vrsta događaja</th>
									<c:if
										test="${vrstaKorisnika eq 'SLUZB' && !utakmica.isZavrsena()}">
										<th class="hidden-phone"></th>
									</c:if>

								</tr>
							</thead>
							<tbody>
								<c:forEach var="entry" items="${dogadjaji}">
									<c:set var="minuta" scope="page"
										value="${entry.getVrijemeDogadjaja()/60}" />
									<c:set var="sekunda" scope="page"
										value="${entry.getVrijemeDogadjaja()%60}" />
									<tr>
										<td class="hidden-phone"
											sorttable_customkey="${entry.getVrijemeDogadjaja()}"><fmt:formatNumber
												pattern="#" value="${minuta-(minuta%1)}" />:<fmt:formatNumber
												pattern="00" value="${sekunda}" /></td>
										<td>${entry.getIgrac().getIme()}
											${entry.getIgrac().getPrezime()}</td>
										<td>${entry.getTip().toString()}</td>
										<c:if
											test="${vrstaKorisnika eq 'SLUZB' && !utakmica.isZavrsena()}">
											<td class="hidden-phone"><button
													onclick="location.href='${pageContext.request.contextPath}/servleti/sluzb/brisiDogadjaj?utakmica=${utakmica.getId()}&dog=${entry.getId()}'"
													type="submit" class="btn btn-xs">BRIŠI DOGAĐAJ</button></td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>

						</table>
					</div>

				</td>


				<td>
					<table style="width: 100%">
						<tr>
							<td style="padding: 0 5px 0 0;"><table
									class="table table-striped table-hover table-users sortable">
									<thead>
										<tr class="hoverable">
											<th class="hidden-phone">Ime igrača</th>
											<th class="hidden-phone">Pozicija</th>
											<th class="hidden-phone sorttable-numeric">Ocjena</th>

											<c:if
												test=" ${vrstaKorisnika eq 'TEHN' && utakmica.getMvp() == null && utakmica.isZavrsena()}">
												<th class="hidden-phone"></th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="entry"
											items="${utakmica.getDomacin().getIgraci()}">
											<tr>
												<td class="hidden-phone hoverable"
													onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
													${entry.getPrezime()}</td>
												<td class="hidden-phone">${entry.getPozicija().toString()}</td>
												<td class="hidden-phone"><fmt:formatNumber
														maxFractionDigits="2" value="${bodovi.get(entry.getId())}" /></td>
												<c:if
													test="${vrstaKorisnika eq 'TEHN' && utakmica.getMvp() == null && utakmica.isZavrsena()}">
													<th class="hidden-phone">
														<button
															onclick="location.href='${pageContext.request.contextPath}/servleti/tehn/mvp?utakmica=${utakmica.getId()}&igrac=${entry.getId()}'"
															type="submit" class="btn btn-xs">Proglasi MVP</button>
													</th>
												</c:if>

											</tr>
										</c:forEach>
									</tbody>

								</table></td>
							<td><table
									class="table table-striped table-hover table-users sortable">
									<thead>
										<tr class="hoverable">
											<th class="hidden-phone">Ime igrača</th>
											<th class="hidden-phone">Pozicija</th>
											<th class="hidden-phone">Ocjena</th>
											<c:if
												test="${vrstaKorisnika == 'TEHN' && utakmica.getMvp() == null && utakmica.isZavrsena()}">
												<th class="hidden-phone"></th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="entry"
											items="${utakmica.getGost().getIgraci()}">
											<tr>
												<td class="hidden-phone hoverable"
													onclick="document.location = '${pageContext.request.contextPath}/servleti/prikaziIgraca?id=${entry.getId()}'">${entry.getIme()}
													${entry.getPrezime()}</td>
												<td class="hidden-phone">${entry.getPozicija().toString()}</td>
												<td class="hidden-phone"><fmt:formatNumber
														maxFractionDigits="2" value="${bodovi.get(entry.getId())}" /></td>
												<c:if
													test="${vrstaKorisnika eq 'TEHN' && utakmica.getMvp() == null && utakmica.isZavrsena()}">
													<th class="hidden-phone">
														<button
															onclick="location.href='${pageContext.request.contextPath}/servleti/tehn/mvp?utakmica=${utakmica.getId()}&igrac=${entry.getId()}'"
															type="submit" class="btn btn-xs">Proglasi MVP</button>
													</th>
												</c:if>

											</tr>
										</c:forEach>
									</tbody>

								</table></td>
						</tr>
					</table>
				</td>
			</tr>

			<c:if test="${vrstaKorisnika eq 'SLUZB' && !utakmica.isZavrsena()}">

				<tr>
					<td>
						<div align="center">

							<a class="btn btn-primary popup-with-form" href="#form-dodajDog">Dodaj
								događaj </a> <a class="btn btn-primary"
								href="${pageContext.request.contextPath}/servleti/sluzb/zavrsiUtakmicu?id=${utakmica.getId()}">Završi
								utakmicu </a>

						</div>

					</td>
				</tr>
			</c:if>

		</tbody>
	</table>

</div>
<c:if test="${vrstaKorisnika eq 'SLUZB' && !utakmica.isZavrsena()}">

	<form id="form-dodajDog"
		class="form-horizontal white-popup-block mfp-hide" method="post"
		action="${pageContext.request.contextPath}/servleti/sluzb/spremiDog">
		<input type="hidden" name="id" value="${utakmica.getId()}">

		<div class="container">
			<div class="form-group">
				<label class="control-label col-sm-2" for="vrijemedogmin"
					id="minLabel">Vrijeme događaja (min): </label>
				<div class="col-sm-10" style="width: 10%">
					<input type="number" pattern="00" step="1" value="0" max="59"
						min="0" class="form-control" id="vrijemedogmin"
						name="vrijemedogmin">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="vrijemedogsec"
					id="secLabel">Vrijeme događaja (sec): </label>
				<div class="col-sm-10" style="width: 10%">
					<input type="number" pattern="00" step="1" value="1" max="59"
						min="0" class="form-control" id="vrijemedogsec"
						name="vrijemedogsec">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="igracDog">Igrač:
				</label>
				<div class="col-sm-10" style="width: 30%">
					<select class="form-control" id="igraciDog" name="igraciDog">
						<c:forEach var="entry"
							items="${utakmica.getDomacin().getIgraci()}">
							<option value="${entry.getId()}">${entry.getIme()}
								${entry.getPrezime()}</option>
						</c:forEach>
						<c:forEach var="entry" items="${utakmica.getGost().getIgraci()}">
							<option value="${entry.getId()}">${entry.getIme()}
								${entry.getPrezime()}</option>
						</c:forEach>
					</select>
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="tipDog">Tip
					Događaja: </label>
				<div class="col-sm-10" style="width: 30%">
					<select class="form-control" id="tipDog" name="tipDog">
						<c:forEach var="entry" items="${tipdog}">
							<option>${entry.toString()}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-sm-offset-2 col-sm-10">
				<button id="submit" type="submit" class="btn btn-primary"
					style="width: 18%;">Zabilježi događaj</button>

			</div>
		</div>
	</form>

</c:if>


<br>
<br>

</body>
</html>