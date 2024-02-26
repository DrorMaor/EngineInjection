package com.maorlamp.engineinjection

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val circleSquareDiameter = 0.7854
        val injection = 0.061

        // DISPLACEMENT CALC
        val tvDisplacementCalc = findViewById<TextView>(R.id.tvDisplacementCalc)
        val lvDisplacementCalc = findViewById<LinearLayout>(R.id.lvDisplacementCalc)

        // Add click listeners to headers
        tvDisplacementCalc.setOnClickListener { toggleContentVisibility(lvDisplacementCalc) }

        // Hide content initially
        lvDisplacementCalc.visibility = View.GONE

        val btnCID = findViewById<Button>(R.id.btnCID)
        btnCID.setOnClickListener {
            val etBore: EditText = findViewById(R.id.etBore)
            val etStroke: EditText = findViewById(R.id.etStroke)
            val etCylinders: EditText = findViewById(R.id.etCylinders)
            val bore: Double = etBore.text.toString().toDouble()
            val stroke: Double = etStroke.text.toString().toDouble()
            val cylinders: Double = etCylinders.text.toString().toDouble()
            val CID: Double = bore.pow(2) * stroke * circleSquareDiameter * cylinders
            val tvCID: TextView = findViewById(R.id.tvCID)
            tvCID.text = CID.toString()
        }
        //////////////////////////////

        // DISPLACEMENT CALC
        val tvInjectorCalcFlowRate = findViewById<TextView>(R.id.tvInjectorCalcFlowRate)
        val lvInjectorCalcFlowRate = findViewById<LinearLayout>(R.id.lvInjectorCalcFlowRate)

        // Add click listeners to headers
        tvInjectorCalcFlowRate.setOnClickListener { toggleContentVisibility(lvInjectorCalcFlowRate) }

        // Hide content initially
        lvInjectorCalcFlowRate.visibility = View.GONE

        val btnNewFlowRate = findViewById<Button>(R.id.btnNewFlowRate)
        btnNewFlowRate.setOnClickListener {
            val etOldPSI: EditText = findViewById(R.id.etOldPSI)
            val etNewPSI: EditText = findViewById(R.id.etNewPSI)
            val etInjectorLbHr: EditText = findViewById(R.id.etInjectorLbHr)
            val oldPSI: Double = etOldPSI.text.toString().toDouble()
            val newPSI: Double = etNewPSI.text.toString().toDouble()
            val injectorLbHr: Double = etInjectorLbHr.text.toString().toDouble()
            val newFlowRate: Double = Math.sqrt(newPSI / oldPSI) * injectorLbHr
            val tvNewFlowRate: TextView = findViewById(R.id.tvNewFlowRate)
            tvNewFlowRate.text = newFlowRate.toString()
        }
        /////////////////


        // INJECTOR TIME CALC
        val tvInjectorTimeCalc = findViewById<TextView>(R.id.tvInjectorTimeCalc)
        val lvInjectorTimeCalc = findViewById<LinearLayout>(R.id.lvInjectorTimeCalc)

        // Add click listeners to headers
        tvInjectorTimeCalc.setOnClickListener { toggleContentVisibility(lvInjectorTimeCalc) }

        // Hide content initially
        lvInjectorTimeCalc.visibility = View.GONE

        val btnInjectorTime = findViewById<Button>(R.id.btnInjectorTime)
        btnInjectorTime.setOnClickListener {
            val etRPM: EditText = findViewById(R.id.etRPM)
            val rpm: Int = etRPM.text.toString().toInt()

            val cyclesSec: Double = (rpm.toDouble() / 2.0) / 60.0
            val tvCyclesSec: TextView = findViewById(R.id.tvCyclesSec)
            tvCyclesSec.text = cyclesSec.toString()

            val totalSecAvail: Double = 1.0 / cyclesSec
            val tvTotalSecAvail: TextView = findViewById(R.id.tvTotalSecAvail)
            tvTotalSecAvail.text = totalSecAvail.toString()

            val secAvail80: Double = totalSecAvail * 0.8
            val tvSecAvail80: TextView = findViewById(R.id.tvSecAvail80)
            tvSecAvail80.text = secAvail80.toString()
        }
        /////////////////


        // COMPRESSION RATIO CALC
        val tvCompressionRatioCalc = findViewById<TextView>(R.id.tvCompressionRatioCalc)
        val lvCompressionRatioCalc = findViewById<LinearLayout>(R.id.lvCompressionRatioCalc)

        // Add click listeners to headers
        tvCompressionRatioCalc.setOnClickListener { toggleContentVisibility(lvCompressionRatioCalc) }

        // Hide content initially
        lvCompressionRatioCalc.visibility = View.GONE

        val btnCalcCompressionRation = findViewById<Button>(R.id.btnCalcCompressionRation)
        btnCalcCompressionRation.setOnClickListener {
            val etCombustionChamberVolumeCC: EditText =
                findViewById(R.id.etCombustionChamberVolumeCC)
            val combustionChamberVolumeCC: Int = etCombustionChamberVolumeCC.text.toString().toInt()
            val etBore2: EditText = findViewById(R.id.etBore2)
            val bore2: Double = etBore2.text.toString().toDouble()
            val etStroke2: EditText = findViewById(R.id.etStroke2)
            val stroke2: Double = etStroke2.text.toString().toDouble()
            val etHeadGasketThickness: EditText = findViewById(R.id.etHeadGasketThickness)
            val headGasketThickness: Double = etHeadGasketThickness.text.toString().toDouble()
            val etHeadGasketBore: EditText = findViewById(R.id.etHeadGasketBore)
            val headGasketBore: Double = etHeadGasketBore.text.toString().toDouble()
            val etPistonToDeckClearance: EditText = findViewById(R.id.etPistonToDeckClearance)
            val pistonToDeckClearance: Double = etPistonToDeckClearance.text.toString().toDouble()
            val etPistonDomeDishVolumeCC: EditText = findViewById(R.id.etPistonDomeDishVolumeCC)
            val pistonDomeDishVolumeCC: Double = etPistonDomeDishVolumeCC.text.toString().toDouble()

            val chamberVolumeCI: Double = combustionChamberVolumeCC * injection
            val tvChamberVolumeCI: TextView = findViewById(R.id.tvChamberVolumeCI)
            tvChamberVolumeCI.text = chamberVolumeCI.toString()

            val cylinderVolume: Double = bore2.pow(2) * stroke2 * circleSquareDiameter
            val tvCylinderVolume: TextView = findViewById(R.id.tvCylinderVolume)
            tvCylinderVolume.text = cylinderVolume.toString()

            val headGasketVolume: Double =
                headGasketBore.pow(2) * headGasketThickness * circleSquareDiameter
            val tvHeadGasketVolume: TextView = findViewById(R.id.tvHeadGasketVolume)
            tvHeadGasketVolume.text = headGasketVolume.toString()

            val PtoDCVolumeCI: Double = bore2.pow(2) * pistonToDeckClearance * circleSquareDiameter
            val tvPtoDCVolumeCI: TextView = findViewById(R.id.tvPtoDCVolumeCI)
            tvPtoDCVolumeCI.text = PtoDCVolumeCI.toString()

            val pistonDomeDishVolumeCI: Double = pistonDomeDishVolumeCC * injection
            val tvPistonDomeDishVolumeCI: TextView = findViewById(R.id.tvPistonDomeDishVolumeCI)
            tvPistonDomeDishVolumeCI.text = pistonDomeDishVolumeCI.toString()

            val volumeAtBDC: Double =
                chamberVolumeCI + headGasketVolume + PtoDCVolumeCI + cylinderVolume - pistonDomeDishVolumeCI
            val tvVolumeAtBDC: TextView = findViewById(R.id.tvVolumeAtBDC)
            tvVolumeAtBDC.text = volumeAtBDC.toString()

            val volumeAtTDC: Double =
                chamberVolumeCI + headGasketVolume + PtoDCVolumeCI - pistonDomeDishVolumeCI
            val tvVolumeAtTDC: TextView = findViewById(R.id.tvVolumeAtTDC)
            tvVolumeAtTDC.text = volumeAtTDC.toString()

            val compressionRatio: Double = volumeAtBDC / volumeAtTDC
            val tvCompressionRatio: TextView = findViewById(R.id.tvCompressionRatio)
            tvCompressionRatio.text = compressionRatio.toString()

        }
        /////////////////

    }

    private fun toggleContentVisibility(content: View) {
        if (content.visibility == View.VISIBLE)
            content.visibility = View.GONE
        else
            content.visibility = View.VISIBLE
    }
}



