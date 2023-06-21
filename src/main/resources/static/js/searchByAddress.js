"use strict";
$(function () {
  //市区町村を選択した際の実装
  $(".municipality").on("click", function () {
    $(".municipality").removeClass("bg-gray-800 hover:bg-gray-800");
    $(".municipality").addClass("bg-gray-500 hover:bg-gray-600");
    const municipalityName = $(this).text();
    $(this).removeClass("bg-gray-500 hover:bg-gray-600");
    $(this).addClass("bg-gray-800 hover:bg-gray-800");
    $("#selected-municipality-name").text(municipalityName);
    $("#municipality-name").val(municipalityName);
  });
  //町域(丁目を除く)を選択した際の実装
  $(document).on("click", "input[name='townId']", function () {
    $(".town-selector")
      .removeClass("bg-gray-500 text-white")
      .addClass("bg-gray-200 hover:bg-gray-300");
    const townName = $(this).siblings("label").find(".town-selector").text();
    $(this)
      .siblings("label")
      .find(".town-selector")
      .removeClass("bg-gray-200 hover:bg-gray-300")
      .addClass("bg-gray-500 text-white");
    $("#selected-town-name").text(townName);
    $("#town-name").val(townName);

    const townId = $(this).val();
    showChoumeList(townId);
  });

  // 丁目を選択した際の実装
  $(document).on("click", "input[name='addressId']", function () {
    $(".choume-selector")
      .removeClass("bg-gray-500 text-white")
      .addClass("bg-gray-200 hover:bg-gray-300");

    const choumeName = $(this)
      .siblings("label")
      .find(".choume-selector")
      .text();

    $(this)
      .siblings("label")
      .find(".choume-selector")
      .removeClass("bg-gray-200 hover:bg-gray-300")
      .addClass("bg-gray-500 text-white");
    $("#selected-choume-name").text(choumeName);
  });

  // 市区町村の決定ボタンをクリックした際の実装
  $("#select-municipality-btn").on("click", function () {
    $("#change-municipality").removeClass("hidden");
    $(".select-municipality").addClass("hidden");
    const municipalityId = $("input[name='municipalityId']:checked").val();
    showTownList(municipalityId);
  });

  // 市区町村の変更ボタンをクリックした際の実装
  $("#change-municipality").on("click", function () {
    $(".select-municipality").removeClass("hidden");
    $("#change-municipality").addClass("hidden");
    $(".select-address").removeClass("flex").addClass("hidden");
    $(".select-choume").addClass("hidden");
    $("#selected-town-name").text("");
    $("#town-name").val(null);
  });

  //町域(丁目を除く)リストを表示
  function showTownList(municipalityId) {
    $.ajax({
      url: "http://localhost:8080/api/getTownList",
      type: "GET",
      dataType: "JSON",
      data: {
        municipalityId: municipalityId,
      },

      async: true,
    })
      .done(function (data) {
        const townList = data.townList;

        let townsDiv = $(".towns");
        townsDiv.empty();
        for (let i = 0; i < townList.length; i++) {
          let townDiv = $("<div>").addClass("town");

          let input = $("<input>")
            .addClass("hidden")
            .prop("type", "radio")
            .prop("name", "townId")
            .prop("id", "t" + townList[i].id)
            .prop("value", townList[i].id);

          let label = $("<label>").prop("for", "t" + townList[i].id);

          let div = $("<div>")
            .addClass(
              "town-selector bg-gray-200 hover:bg-gray-300 pl-2 py-1 cursor-pointer"
            )
            .addClass(
              i + 1 == townList.length ? "" : "border-b-2 border-gray-500"
            )
            .text(townList[i].name);
          label.append(div);
          townDiv.append(input);
          townDiv.append(label);
          townsDiv.append(townDiv);
        }
        $(".select-address").removeClass("hidden");
        $(".select-address").addClass("flex");
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  }

  function showChoumeList(townId) {
    $.ajax({
      url: "http://localhost:8080/api/getChoumeList",
      type: "GET",
      dataType: "JSON",
      data: {
        townId: townId,
      },

      async: true,
    })
      .done(function (data) {
        //丁目選択部分をリセット
        let choumeListDiv = $(".choumeList");
        choumeListDiv.empty();
        //丁目リストを取得
        const choumeList = data.choumeList;

        if (choumeList.length == 1 && choumeList[0].name == null) {
          let input = $("<input>")
            .prop("type", "hidden")
            .prop("name", "addressId")
            .prop("value", choumeList[0].id);

          choumeListDiv.append(input);
          $(".select-choume").addClass("hidden");

          return;
        }

        for (let i = 0; i < choumeList.length; i++) {
          if (choumeList[i].name == null) {
            continue;
          }
          let choumeDiv = $("<div>").addClass("choume");

          let input = $("<input>")
            .addClass("hidden")
            .prop("type", "radio")
            .prop("name", "addressId")
            .prop("id", "c" + choumeList[i].id)
            .prop("value", choumeList[i].id);

          let label = $("<label>").prop("for", "c" + choumeList[i].id);

          let div = $("<div>")
            .addClass(
              "choume-selector bg-gray-200 hover:bg-gray-300 pl-2 py-1 cursor-pointer"
            )
            .addClass(
              i + 1 == choumeList.length ? "" : "border-b-2 border-gray-500"
            )
            .text(choumeList[i].name);

          label.append(div);
          choumeDiv.append(input);
          choumeDiv.append(label);

          choumeListDiv.append(choumeDiv);
        }

        $(".select-choume").removeClass("hidden");
      })
      .fail(function (XMLHttpRequest, textStatus, errorThrown) {
        // 検索失敗時には、その旨をダイアログ表示

        console.log("XMLHttpRequest : " + XMLHttpRequest.status);
        console.log("textStatus     : " + textStatus);
        console.log("errorThrown    : " + errorThrown.message);
      });
  }
});
