rootProject.name = "kotlin-batch"


include(
    "boot",
    "cloud",
    "data",
    ":data:mysql",
    ":data:h2",
    ":data:orcale",
    "core"
)
